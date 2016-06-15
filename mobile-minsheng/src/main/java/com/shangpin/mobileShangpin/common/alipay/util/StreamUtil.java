/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2006 All Rights Reserved.
 */
package com.shangpin.mobileShangpin.common.alipay.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * �������Ĺ�����
 * 
 * @author stone.zhangjl
 * @version $Id: StreamUtil.java, v 0.1 2008-8-21 ����05:05:07 stone.zhangjl Exp $
 */
public class StreamUtil {
	private static final int DEFAULT_BUFFER_SIZE = 8192;

	/**
	 * ����������ȡ����, д�뵽�������. �˷���ʹ�ô�СΪ8192�ֽڵ�Ĭ�ϵĻ�����.
	 * 
	 * @param in
	 *            ������
	 * @param out
	 *            �����
	 * 
	 * @throws IOException
	 *             ��������쳣
	 */
	public static void io(InputStream in, OutputStream out) throws IOException {
		io(in, out, -1);
	}

	/**
	 * ����������ȡ����, д�뵽�������. ʹ��ָ����С�Ļ�����.
	 * 
	 * @param in
	 *            ������
	 * @param out
	 *            �����
	 * @param bufferSize
	 *            �������С(�ֽ���)
	 * 
	 * @throws IOException
	 *             ��������쳣
	 */
	public static void io(InputStream in, OutputStream out, int bufferSize) throws IOException {
		if (bufferSize == -1) {
			bufferSize = DEFAULT_BUFFER_SIZE;
		}
		byte[] buffer = new byte[bufferSize];
		int amount;
		while ((amount = in.read(buffer)) >= 0) {
			out.write(buffer, 0, amount);
		}
	}

	/**
	 * ����������ȡ����, д�뵽�������. �˷���ʹ�ô�СΪ4096�ַ��Ĭ�ϵĻ�����.
	 * 
	 * @param in
	 *            ������
	 * @param out
	 *            �����
	 * 
	 * @throws IOException
	 *             ��������쳣
	 */
	public static void io(Reader in, Writer out) throws IOException {
		io(in, out, -1);
	}

	/**
	 * ����������ȡ����, д�뵽�������. ʹ��ָ����С�Ļ�����.
	 * 
	 * @param in
	 *            ������
	 * @param out
	 *            �����
	 * @param bufferSize
	 *            �������С(�ַ���)
	 * 
	 * @throws IOException
	 *             ��������쳣
	 */
	public static void io(Reader in, Writer out, int bufferSize) throws IOException {
		if (bufferSize == -1) {
			bufferSize = DEFAULT_BUFFER_SIZE >> 1;
		}
		char[] buffer = new char[bufferSize];
		int amount;
		while ((amount = in.read(buffer)) >= 0) {
			out.write(buffer, 0, amount);
		}
	}

	/**
	 * ȡ��ͬ�����������.
	 * 
	 * @param out
	 *            Ҫ���������
	 * 
	 * @return �̰߳�ȫ��ͬ���������
	 */
	public static OutputStream synchronizedOutputStream(OutputStream out) {
		return new SynchronizedOutputStream(out);
	}

	/**
	 * ȡ��ͬ�����������.
	 * 
	 * @param out
	 *            Ҫ���������
	 * @param lock
	 *            ͬ����
	 * 
	 * @return �̰߳�ȫ��ͬ���������
	 */
	public static OutputStream synchronizedOutputStream(OutputStream out, Object lock) {
		return new SynchronizedOutputStream(out, lock);
	}

	/**
	 * ��ָ���������������ı�ȫ��������һ���ַ���.
	 * 
	 * @param in
	 *            Ҫ��ȡ��������
	 * 
	 * @return ����������ȡ�õ��ı�
	 * 
	 * @throws IOException
	 *             ��������쳣
	 */
	public static String readText(InputStream in) throws IOException {
		return readText(in, null, -1);
	}

	/**
	 * ��ָ���������������ı�ȫ��������һ���ַ���.
	 * 
	 * @param in
	 *            Ҫ��ȡ��������
	 * @param encoding
	 *            �ı����뷽ʽ
	 * 
	 * @return ����������ȡ�õ��ı�
	 * 
	 * @throws IOException
	 *             ��������쳣
	 */
	public static String readText(InputStream in, String encoding) throws IOException {
		return readText(in, encoding, -1);
	}

	/**
	 * ��ָ���������������ı�ȫ��������һ���ַ���.
	 * 
	 * @param in
	 *            Ҫ��ȡ��������
	 * @param encoding
	 *            �ı����뷽ʽ
	 * @param bufferSize
	 *            �������С(�ַ���)
	 * 
	 * @return ����������ȡ�õ��ı�
	 * 
	 * @throws IOException
	 *             ��������쳣
	 */
	public static String readText(InputStream in, String encoding, int bufferSize) throws IOException {
		Reader reader = (encoding == null) ? new InputStreamReader(in) : new InputStreamReader(in, encoding);
		return readText(reader, bufferSize);
	}

	/**
	 * ��ָ��<code>Reader</code>�������ı�ȫ��������һ���ַ���.
	 * 
	 * @param reader
	 *            Ҫ��ȡ��<code>Reader</code>
	 * 
	 * @return ��<code>Reader</code>��ȡ�õ��ı�
	 * 
	 * @throws IOException
	 *             ��������쳣
	 */
	public static String readText(Reader reader) throws IOException {
		return readText(reader, -1);
	}

	/**
	 * ��ָ��<code>Reader</code>�������ı�ȫ��������һ���ַ���.
	 * 
	 * @param reader
	 *            Ҫ��ȡ��<code>Reader</code>
	 * @param bufferSize
	 *            ������Ĵ�С(�ַ���)
	 * 
	 * @return ��<code>Reader</code>��ȡ�õ��ı�
	 * 
	 * @throws IOException
	 *             ��������쳣
	 */
	public static String readText(Reader reader, int bufferSize) throws IOException {
		StringWriter writer = new StringWriter();
		io(reader, writer, bufferSize);
		return writer.toString();
	}

	/**
	 * ͬ����������������.
	 */
	private static class SynchronizedOutputStream extends OutputStream {
		private OutputStream out;
		private Object lock;

		SynchronizedOutputStream(OutputStream out) {
			this(out, out);
		}

		SynchronizedOutputStream(OutputStream out, Object lock) {
			this.out = out;
			this.lock = lock;
		}

		public void write(int datum) throws IOException {
			synchronized (lock) {
				out.write(datum);
			}
		}

		public void write(byte[] data) throws IOException {
			synchronized (lock) {
				out.write(data);
			}
		}

		public void write(byte[] data, int offset, int length) throws IOException {
			synchronized (lock) {
				out.write(data, offset, length);
			}
		}

		public void flush() throws IOException {
			synchronized (lock) {
				out.flush();
			}
		}

		public void close() throws IOException {
			synchronized (lock) {
				out.close();
			}
		}
	}
}
