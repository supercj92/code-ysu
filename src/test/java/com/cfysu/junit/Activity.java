//package com.cfysu.junit;
//
//import com.alibaba.cxdc.knowledgecloud.alimi.constants.TimeType;
//import com.alibaba.cxdc.knowledgecloud.alimi.dto.ActivityAreaDto;
//import com.alibaba.cxdc.knowledgecloud.alimi.dto.ActivitySummary;
//import com.alibaba.cxdc.knowledgecloud.alimi.util.ActivityAreaUtils;
//import com.alibee.shop.api.bean.Answer;
//import com.alibee.shop.api.bean.AnswerItem;
//import com.alibee.shop.api.bean.Type;
//import com.alibee.shop.biz.algo.common.*;
//import com.alibee.shop.biz.utils.AnswerUtil;
//import com.alibee.shop.biz.utils.ThreadlocalUtil;
//import com.alibee.shop.biz.utils.WhiteListUtil;
//import com.taobao.alibee.algorithm.api.AlibeeMrcService;
//import com.taobao.alibee.algorithm.bean.MrcParam;
//import com.taobao.alibee.algorithm.bean.MrcResult;
//import com.taobao.hsf.app.spring.util.annotation.HSFConsumer;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Component
//public class ActivityAnswerManager extends BaseManager {
//    private final static Log logger = LogFactory.getLog(ActivityAnswerManager.class);
//    private static final String ACTIVITY_SCENE = "activityScene";
//    private static final String TITLE = "亲请选择您要问的活动";
//    private static final String ITEM_LINK_FORMAT = "https://item.taobao.com/item.htm?id=%s";
//    private static final String WHICH_PRODUCT = "能提供亲想咨询的宝贝链接吗？";
//    private static final Map<Integer, String> NUM_2_STR_MAP = new HashMap<>(8);
//    private static final String COMMA = "、";
//
//    static {
//        NUM_2_STR_MAP.put(1, "一");
//        NUM_2_STR_MAP.put(2, "二");
//        NUM_2_STR_MAP.put(3, "三");
//        NUM_2_STR_MAP.put(4, "四");
//        NUM_2_STR_MAP.put(5, "五");
//        NUM_2_STR_MAP.put(6, "六");
//    }
//
//    @Autowired
//    private ActivityManager activityManager;
//
//    @Autowired
//    private DiamondManager diamondManager;
//
//    @HSFConsumer(serviceGroup = "HSF", serviceVersion = "${alibee.algorithm.mrc.hsf.version}", clientTimeout = 100)
//    private AlibeeMrcService alibeeMrcService;
//
//    public Answer activity(long innerId, IntentionQuery query, Intention intention) {
//        try {
//            if(intention != null && intention.getShopActivityResult() != null) {
//                ThreadlocalUtil.set("activityForProcess", Boolean.TRUE);
//                ShopActivityResult result = intention.getShopActivityResult();
//                ActivitySummary current = result.getCurrentActivity();
//                List<ActivitySummary> activities = result.getActivitys();
//                ThreadlocalUtil.set("activityForAspect", result.getActivityAspect());
//                if(result.isNeedItem()) {
//                    ThreadlocalUtil.set("activityForType", "needItem");
//                    return AnswerUtil.textAnswer(intention.getPackName(), WHICH_PRODUCT, ACTIVITY_SCENE);
//                } else if(current != null) {
//                    ThreadlocalUtil.set("activityForType", "current");
//                    ThreadlocalUtil.set("activityForId", current.getId());
//                    ActivityAreaDto activityAreaDto = activityManager.queryActivityById(innerId, current.getId());
//                    if(result.getActivityAspect() != null && ActivityAspect.LIST != result.getActivityAspect()) {
//                        Map<String, String> ret = buildAnswer(Long.valueOf(query.getSeller().getId()), activityAreaDto, result.getActivityAspect(), query.getQuery());
//                        if(!StringUtils.isEmpty(ret.get("pic"))) {
//                            return AnswerUtil.picAnswer(intention.getPackName(), StringUtils.trimToEmpty(ret.get("content")), ret.get("pic"), ACTIVITY_SCENE);
//                        } else if(!StringUtils.isEmpty(ret.get("content"))) {
//                            return AnswerUtil.textAnswer(intention.getPackName(), ret.get("content"), ACTIVITY_SCENE);
//                        }
//                    } else {
//                        if(result.getActivityAspect() == null && !StringUtils.isEmpty(activityAreaDto.getRule()) && EventType.CLICK_ACTIVITY != query.getEventType()) {
//                            String doc = diamondManager.isInvokeMrcActivity() ? generateDoc(activityAreaDto) : activityAreaDto.getRule();
//                            String mrc = mrcInvoke(Long.valueOf(query.getSeller().getId()), query.getQuery(), doc);
//                            if (!StringUtils.isEmpty(mrc)) {
//                                return AnswerUtil.textAnswer(intention.getPackName(), mrc, ACTIVITY_SCENE);
//                            }
//                        }
//                        if(!StringUtils.isEmpty(activityAreaDto.getPic())) {
//                            return AnswerUtil.picAnswer(intention.getPackName(), buildActivityDesc(activityAreaDto), activityAreaDto.getPic(), ACTIVITY_SCENE);
//                        } else {
//                            return AnswerUtil.textAnswer(intention.getPackName(), buildActivityDesc(activityAreaDto), ACTIVITY_SCENE);
//                        }
//                    }
//                } else if(activities != null && activities.size() > 0) {
//                    ThreadlocalUtil.set("activityForType", "list");
//                    return listActivityAnswer(intention.getPackName(), activities, query.getQuery());
//                }
//            }
//        } catch (Exception e) {
//            logger.error("activity error", e);
//        }
//        return null;
//    }
//
//    /**
//     * 活动专区-新逻辑
//     */
//    public Answer generateActivityAnswer(long innerId, IntentionQuery query, Intention intention){
//        if(intention == null || intention.getShopActivityResult() == null){
//            return null;
//        }
//        return null;
//    }
//
//    private String activityTime(ActivityAreaDto activityAreaDto) {
//        String timeSpan = ActivityAreaUtils.getTimeStrBy(activityAreaDto);
//        if(!StringUtils.isEmpty(timeSpan)) {
//            StringBuilder sbd = new StringBuilder();
//            sbd.append("您咨询的活动").append(activityAreaDto.getName()).append("，活动时间是").append(timeSpan).append("哦");
//            return sbd.toString();
//        } else {
//            return null;
//        }
//    }
//
//    private Map<String, String> buildAnswer(long sellerId, ActivityAreaDto activityAreaDto, ActivityAspect aspect, String query) {
//        Map<String, String> ret = new HashMap<>();
//        StringBuilder sbd = new StringBuilder();
//        if(ActivityAreaUtils.isPreActivity(activityAreaDto, System.currentTimeMillis())) {
//            sbd.append(activityAreaDto.getPreReply()).append("\n\r");
//            if(ActivityAspect.SHI_JIAN != aspect) {
//                sbd.append(activityTime(activityAreaDto)).append("\n\r");
//            }
//        }
//        boolean isInvalid = ActivityAreaUtils.is72InvalidActivity(activityAreaDto, System.currentTimeMillis());
//        if(aspect != null && !isInvalid){
//            if(ActivityAspect.BAO_BEI == aspect) {
//                if(!StringUtils.isEmpty(activityAreaDto.getItemIds())) {
//                    String[] items = StringUtils.split(activityAreaDto.getItemIds(), ",");
//                    if(items != null && items.length > 0) {
//                        for(int i = 0;i < items.length && i < 5;i ++) {
//                            sbd.append(String.format(ITEM_LINK_FORMAT, items[i].trim())).append("\n\r");
//                        }
//                    }
//                } else {
//                    sbd.append("所有宝贝");
//                }
//            } else {
//                appendAnswer(aspect, activityAreaDto, sbd, sellerId, query, ret);
//            }
//        } else if(isInvalid) {
//            if(!StringUtils.isEmpty(activityAreaDto.getReply())) {
//                sbd.append(activityAreaDto.getReply());
//            } else {
//                sbd.append(activityTime(activityAreaDto)).append("\n\r");
//                if(!StringUtils.isEmpty(activityAreaDto.getRule())) {
//                    sbd.append(activityAreaDto.getRule());
//                }
//            }
//        }
//        ret.put("content", sbd.toString());
//        return ret;
//    }
//
//    private String mrcInvoke(long sellerId, String query, String doc) {
//        try {
//            if(diamondManager.isInvokeMrc() && WhiteListUtil.isInMrc(sellerId)) {
//                return mrcTrueInvoke(query, doc);
//            }
//        } catch (Exception e) {
//            logger.error("mrcInvoke error", e);
//        }
//        return null;
//    }
//
//    public String mrcTrueInvoke(String query, String doc) {
//        try {
//            ThreadlocalUtil.set("mrcInvoke", Boolean.TRUE);
//            MrcParam param = new MrcParam();
//            param.setQuery(query);
//            param.setDoc(doc);
//            MrcResult result = alibeeMrcService.invokeRead(param);
//            if(result != null && result.isSuccess() && !StringUtils.isEmpty(result.getAnswer())) {
//                ThreadlocalUtil.set("mrcAnswer", Boolean.TRUE);
//                return result.getAnswer();
//            }
//        } catch (Exception e) {
//            logger.error("mrcTrueInvoke error", e);
//        }
//        return null;
//    }
//
//    private String buildActivityDesc(ActivityAreaDto activityAreaDto) {
//        if(activityAreaDto != null) {
//            if(!StringUtils.isEmpty(activityAreaDto.getRule())) {
//                return activityAreaDto.getRule();
//            }
//        }
//        return "";
//    }
//
//    private AnswerItem buildAnswerItem(long id, String content, String query) {
//        AnswerItem item = new AnswerItem();
//        item.setId("" + id + "&" +query);
//        item.setContent(content);
//        return item;
//    }
//
//    private Answer listActivityAnswer(String source, List<ActivitySummary> activities, String query) {
//        List<AnswerItem> items = new ArrayList<>();
//        for(ActivitySummary activity : activities) {
//            items.add(buildAnswerItem(activity.getId(), activity.getName(), query));
//        }
//        return AnswerUtil.listAnswer(source, ACTIVITY_SCENE, items, TITLE, Type.ACTIVITY);
//    }
//
//    /**
//     * 通过开关判断，是否全部字段调用mrc接口
//     */
//    private void appendAnswer(ActivityAspect aspect, ActivityAreaDto activityAreaDto, StringBuilder sbd, Long sellerId, String query, Map<String, String> ret){
//
//        if(diamondManager.isInvokeMrcActivity()){
//            //新逻辑
//            appendAnswerByMRC(aspect, activityAreaDto, sbd, sellerId, query, ret);
//        }else {
//            //原有逻辑
//            appendAnswerByAspect(aspect, activityAreaDto, sbd, sellerId, query, ret);
//        }
//    }
//
//    private void appendAnswerByAspect(ActivityAspect aspect, ActivityAreaDto activityAreaDto, StringBuilder sbd, Long sellerId, String query, Map<String, String> ret){
//        if(ActivityAspect.DUI_XIANG == aspect) {
//            if(!StringUtils.isEmpty(activityAreaDto.getPeopleGroup())) {
//                sbd.append("参与人群：").append(activityAreaDto.getPeopleGroup());
//            } else {
//                sbd.append("参与人群：所有用户");
//            }
//        } else if(ActivityAspect.GUI_ZE == aspect) {
//            if(!StringUtils.isEmpty(activityAreaDto.getRule())) {
//                sbd.append("活动规则：").append(activityAreaDto.getRule());
//            }
//        } else if(ActivityAspect.JIANG_PIN == aspect) {
//            if(!StringUtils.isEmpty(activityAreaDto.getWinningPic())) {
//                ret.put("pic", activityAreaDto.getWinningPic());
//            }
//            if(!StringUtils.isEmpty(activityAreaDto.getWinningReply())) {
//                sbd.append(activityAreaDto.getWinningReply());
//            }
//        } else if(ActivityAspect.LIAN_JIE == aspect) {
//            if(!StringUtils.isEmpty(activityAreaDto.getUrl())) {
//                sbd.append("活动链接：").append(activityAreaDto.getUrl());
//            }
//        } else if(ActivityAspect.SHI_JIAN == aspect) {
//            String timeInfo = activityTime(activityAreaDto);
//            if(!StringUtils.isEmpty(timeInfo)) {
//                sbd.append(timeInfo);
//            }
//        } else if(ActivityAspect.SHOU_HOU == aspect) {
//            if(!StringUtils.isEmpty(activityAreaDto.getAfterSalesRule())) {
//                sbd.append(activityAreaDto.getAfterSalesRule());
//            }
//        }
//    }
//
//    private void appendAnswerByMRC(ActivityAspect aspect, ActivityAreaDto activityAreaDto, StringBuilder sbd, Long sellerId, String query, Map<String, String> ret){
//
//        if(activityAreaDto == null){
//            return;
//        }
//
//        String doc = generateDoc(activityAreaDto);
//        if(StringUtils.isBlank(doc)){
//            return;
//        }
//
//        String lastQuery = ThreadlocalUtil.get("activityLastQuery");
//        if(StringUtils.isNotBlank(lastQuery)){
//            query = lastQuery;
//        }
//
//        String mrcRes = mrcInvoke(sellerId, query, doc);
//        if(StringUtils.isNotBlank(mrcRes)){
//            if(ActivityAspect.SHI_JIAN == aspect){
//                sbd.append("您咨询的活动:").append(activityAreaDto.getName()).append("，活动时间是:").append(mrcRes).append("哦");
//            }else {
//                sbd.append(mrcRes);
//            }
//            if(ActivityAspect.JIANG_PIN == aspect && StringUtils.isNotBlank(activityAreaDto.getWinningPic())){
//                ret.put("pic", activityAreaDto.getWinningPic());
//            }
//        }else {
//            //机器阅读接口返回为空，则走老逻辑兜底
//            appendAnswerByAspect(aspect, activityAreaDto, sbd, sellerId, query, ret);
//        }
//    }
//
//    private String generateDoc(ActivityAreaDto activityAreaDto){
//
//        if(activityAreaDto == null){
//            return null;
//        }
//
//        StringBuilder docSbd = new StringBuilder();
//        int count = 1;
//        //拼接文章
//        String timeStr = ActivityAreaUtils.getTimeStrBy(activityAreaDto);
//
//        //如果类型为间隔时间，调整时间格式。mrc接口需要
//        if(Integer.valueOf(TimeType.INTERVAL.getValue()).equals(activityAreaDto.getTimeType())){
//            Long startTime = activityAreaDto.getStart();
//            Long endTime = activityAreaDto.getEnd();
//            if (null != startTime && null != endTime) {
//                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
//                String start = format.format(new Date(startTime));
//                String end = format.format(new Date(endTime));
//                timeStr = start + "-" + end;
//            }
//        }
//
//        if(StringUtils.isNotBlank(timeStr)){
//            docSbd.append(NUM_2_STR_MAP.get(count++)).append("、活动时间\n").append(timeStr).append("\n");
//        }
//
//        String peopleStr = StringUtils.isBlank(activityAreaDto.getPeopleGroup()) ? "所有用户" : activityAreaDto.getPeopleGroup();
//        docSbd.append(NUM_2_STR_MAP.get(count++)).append("、参与人群\n").append(peopleStr).append("\n");
//
//        if(StringUtils.isNotBlank(activityAreaDto.getRule())){
//            docSbd.append(NUM_2_STR_MAP.get(count++)).append("、活动规则\n").append(activityAreaDto.getRule()).append("\n");
//        }
//
//        if(StringUtils.isNotBlank(activityAreaDto.getUrl())){
//            docSbd.append(NUM_2_STR_MAP.get(count++)).append("、活动链接\n").append(activityAreaDto.getUrl()).append("\n");
//        }
//
//        if(StringUtils.isNotBlank(activityAreaDto.getAfterSalesRule())){
//            docSbd.append(NUM_2_STR_MAP.get(count++)).append("、活动售后\n").append(activityAreaDto.getAfterSalesRule()).append("\n");
//        }
//
//        if(StringUtils.isNotBlank(activityAreaDto.getWinningReply())){
//            docSbd.append(NUM_2_STR_MAP.get(count++)).append("、中奖领取\n").append(activityAreaDto.getWinningReply());
//        }
//
//        return docSbd.toString();
//    }
//}
//
