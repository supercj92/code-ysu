package com.cfysu.nlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorFactory;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;
import opennlp.tools.langdetect.LanguageDetectorSampleStream;
import opennlp.tools.langdetect.LanguageSample;
import opennlp.tools.ml.perceptron.PerceptronTrainer;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.ModelUtil;

/**
 * @Author canglong
 * @Date 2021/11/24
 */
public class OpenNlpMain {

    private static String tokens[] = new String[]{
        "Pierre",
        "Vinken",
        "is",
        "61",
        "years",
        "old",
        "."
    };

    private static String tokensCN[] = new String[]{
        "王二狗",
        "今年",
        "60",
        "岁",
        "了",
        "。"
    };

    public static void main(String[] args) throws Exception {
        //trainModel();
        //detectLanguage();
        findEntity();
    }

    public static void findEntity () throws Exception{
        try (InputStream modelIn = new FileInputStream("/Users/chris/nlp/model/en-ner-person.bin")){
            TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
            NameFinderME nameFinder = new NameFinderME(model);

            Span[] spans = nameFinder.find(tokens);
            Arrays.stream(spans).forEach(System.out::println);
        }
    }

    public static void detectLanguage() throws IOException {
        try (InputStream modelIn = new FileInputStream(new File("/Users/chris/nlp/model/langdetect-183.bin"))) {
            LanguageDetectorModel model = new LanguageDetectorModel(modelIn);

            String inputText = "hello";
            LanguageDetector myCategorizer = new LanguageDetectorME(model);

            // Get the most probable language
            Language bestLanguage = myCategorizer.predictLanguage(inputText);
            System.out.println("Best language: " + bestLanguage.getLang());
            System.out.println("Best language confidence: " + bestLanguage.getConfidence());
        }
    }

    public static void trainModel() throws Exception{
        InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File("/Users/chris/nlp/corpora/lang_detecte.data"));

        ObjectStream<String> lineStream =
            new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);
        ObjectStream<LanguageSample> sampleStream = new LanguageDetectorSampleStream(lineStream);

        TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
        params.put(TrainingParameters.ALGORITHM_PARAM,
            PerceptronTrainer.PERCEPTRON_VALUE);
        params.put(TrainingParameters.CUTOFF_PARAM, 0);

        LanguageDetectorFactory factory = new LanguageDetectorFactory();

        LanguageDetectorModel model = LanguageDetectorME.train(sampleStream, params, factory);
        model.serialize(new File("langdetect.bin"));
    }

}
