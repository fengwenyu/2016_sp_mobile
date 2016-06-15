package com.shangpin.mobileAolai.common.util;

import java.awt.Color;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.DeformedRandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

public class DefaultImagCatptchaEngine extends ListImageCaptchaEngine {
    protected void buildInitialFactories() {
        WordGenerator wgen = new RandomWordGenerator("ABCDEFGHIJKMNPQRSTUVWXYZ123456789");
        // RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(new int[] { 0, 100 }, new int[] { 0, 100 }, new int[] { 0, 100 });
        // 文字显示的个数
        // TextPaster textPaster = new RandomTextPaster(new Integer(4), new Integer(4), cgen, true);
        // 图片的大小
        BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(new Integer(95), new Integer(40));
        // 字体格式
        // Font[] fontsList = new Font[] { new Font("Arial", 0, 20), new Font("Tahoma", 0, 20) };
        DeformedRandomFontGenerator fonts = new DeformedRandomFontGenerator(new Integer(18), new Integer(18));
        // 文字的大小
        // FontGenerator fontGenerator = new RandomFontGenerator(new Integer(20), new Integer(20), fontsList);
        RandomTextPaster textPaster = new RandomTextPaster(new Integer(4), new Integer(4), Color.BLACK);
        WordToImage wordToImage = new ComposedWordToImage(fonts, backgroundGenerator, textPaster);
        this.addFactory(new GimpyFactory(wgen, wordToImage));
    }
}
