package com.shangpin.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.lang.math.RandomUtils;
import org.patchca.background.BackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.filter.ConfigurableFilterFactory;
import org.patchca.filter.library.AbstractImageOp;
import org.patchca.filter.library.WobbleImageOp;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.text.renderer.TextRenderer;
import org.patchca.word.RandomWordFactory;

/**
 * 图像验证码工具,默认输出4个数字，宽高：82*32
 * @author 陈小峰
 *
 */
public class CaptchaCodeUtil {
	private static ConfigurableCaptchaService cs = null;

	private static RandomFontFactory fontFactory = null;

	private static RandomWordFactory defaultWordFactory = null;

	private static TextRenderer textRenderer = null;
	
	static{
		init();
	}
	/**
	 * 将验证码图片写到文件中
	 * @param path 文件路径
	 * @return  图片中字符串值
	 */
	public static String img2File(String path){
		setDefault();
		Captcha captcha = cs.getCaptcha(); 
		try {
			return img2Stream(captcha, new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	/**
	 * 将验证码图片写到输出流当中
	 * @param stream 输出流
	 * @return 图片中字符串值
	 */
	public static String img2Stream(OutputStream stream){
		setDefault();
		Captcha captcha = cs.getCaptcha(); 
		return img2Stream(captcha, stream);
		
	}
	
	/**
	 * 将图片写到输出流当中
	 * @param stream 输出流
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @return 图片中的字符串值
	 */
	public static String img2Stream(OutputStream stream,int width,int height){
		cs.setWordFactory(getDefaultWord());
		cs.setWidth(width);cs.setHeight(height);
		cs.setFontFactory(calcFont(width,height));
		Captcha captcha = cs.getCaptcha(); 
		return img2Stream(captcha, stream);
	}
	/**
	 * 将图片写到输出流当中
	 * @param stream 输出流
	 * @param chars 图片中需要显示的字符
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @return 图片中的字符串值
	 */
	public static String img2Stream(OutputStream stream,String chars,int width,int height){
		RandomWordFactory wordFactory = new RandomWordFactory();
		wordFactory.setCharacters(chars);
		wordFactory.setMaxLength(4);
		wordFactory.setMinLength(4);
		cs.setWordFactory(wordFactory);
		cs.setWidth(width);
		cs.setHeight(height);
		cs.setFontFactory(calcFont(width,height));
		Captcha captcha = cs.getCaptcha(); 
		return img2Stream(captcha, stream);
	}
	/**
	 * 将图片写到输出流当中
	 * @param stream 输出流
	 * @param chars 图片中需要显示的字符
	 * @return 图片中的字符串值
	 */
	public static String img2Stream(OutputStream stream,String chars){
		RandomWordFactory wordFactory = new RandomWordFactory();
		wordFactory.setCharacters(chars);
		wordFactory.setMaxLength(4);
		wordFactory.setMinLength(4);
		cs.setWordFactory(wordFactory);
		Captcha captcha = cs.getCaptcha(); 
		return img2Stream(captcha, stream);
	}
	/**
	 * 根据输出的宽高计算字体的范围
	 * @param width
	 * @param height
	 * @return
	 */
	private static RandomFontFactory calcFont(int width,int height){
		int mj=Math.max(width,height);
		RandomFontFactory fc = new RandomFontFactory();
		fc.setMaxSize(mj/6+8);
		fc.setMinSize(mj/6);
		return fc;
	}
	/**
	 * 默认0-9的字符，82的宽，32的高
	 */
	private static void setDefault(){
		// 验证码图片的大小
		cs.setWidth(82);
		cs.setHeight(32);
		cs.setWordFactory(getDefaultWord());
		cs.setFontFactory(defaultFont());
	}
	private static RandomFontFactory defaultFont(){
		// 随机字体生成器
		if(fontFactory==null){
			fontFactory = new RandomFontFactory();
			fontFactory.setMaxSize(28);
			fontFactory.setMinSize(16);
		}
		return fontFactory;
	}
	private static RandomWordFactory getDefaultWord(){
		if(defaultWordFactory==null){
			defaultWordFactory = new RandomWordFactory();
			defaultWordFactory.setCharacters("0123456789");
			defaultWordFactory.setMaxLength(4);
			defaultWordFactory.setMinLength(4);
		}
		return defaultWordFactory;
	}
	private static String img2Stream(Captcha captcha,OutputStream stream){
		setFilter(captcha);
		String vc = captcha.getChallenge();
		BufferedImage bufferedImage = captcha.getImage(); 
		try {
			ImageIO.write(bufferedImage, "png", stream);
		} catch (IOException e) {
			return null;
		}
		return vc;
	}
	
	static void setFilter(Captcha captcha ){
		cs.setFilterFactory(filterFactory());
	}
	public static void init(){
		cs = new ConfigurableCaptchaService(); 
		// 颜色创建工厂,使用一定范围内的随机色
		cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = RandomUtils.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = RandomUtils.nextInt(71);
                    } else {
                        c[fi] = RandomUtils.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });
		
		// 随机字符生成器,去除掉容易混淆的字母和数字,如o和0等
		// 自定义验证码图片背景
		MyCustomBackgroundFactory backgroundFactory = new MyCustomBackgroundFactory();
		cs.setBackgroundFactory(backgroundFactory);
		// 图片滤镜设置
		//cs.setFilterFactory(filterFactory);
		// 文字渲染器设置
		textRenderer = new BestFitTextRenderer();
		textRenderer.setBottomMargin(3);
		textRenderer.setTopMargin(3);
		cs.setTextRenderer(textRenderer);
		
	}
	
	private static ConfigurableFilterFactory filterFactory() {
		ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
		
		List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
		
		WobbleImageOp wobbleImageOp = new WobbleImageOp();
		
		wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_MIRROR);
		
		wobbleImageOp.setxAmplitude(3.0);
		
		wobbleImageOp.setyAmplitude(3.0);
		
		filters.add(wobbleImageOp);
		
		filterFactory.setFilters(filters);
		return filterFactory;
	}

	private static class MyCustomBackgroundFactory implements BackgroundFactory {

		private Random random = new Random();

		public void fillBackground(BufferedImage image) {
			Graphics graphics = image.getGraphics();
			// 验证码图片的宽高
			int imgWidth = image.getWidth();
			int imgHeight = image.getHeight();
			// 填充为白色背景
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, imgWidth, imgHeight);
			// 画100个噪点(颜色及位置随机)
			for (int i = 0; i < (imgHeight*imgWidth)/24; i++) {
				// 随机颜色
				int rInt = random.nextInt(255);
				int gInt = random.nextInt(255);
				int bInt = random.nextInt(255);
				graphics.setColor(new Color(rInt, gInt, bInt));
				// 随机位置
				int xInt = random.nextInt(imgWidth - 3);
				int yInt = random.nextInt(imgHeight - 2);
				// 随机旋转角度
				int sAngleInt = random.nextInt(360);
				int eAngleInt = random.nextInt(360);
				// 随机大小
				int wInt = random.nextInt(6);
				int hInt = random.nextInt(6);
				graphics.fillArc(xInt, yInt, wInt, hInt, sAngleInt, eAngleInt);
				// 画5条干扰线
				if (i % 60 == 0) {
					int xInt2 = random.nextInt(imgWidth);
					int yInt2 = random.nextInt(imgHeight);
					graphics.drawLine(xInt, yInt, xInt2, yInt2);
				}

			}

		}

	}
}
