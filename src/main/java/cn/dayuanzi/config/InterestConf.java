package cn.dayuanzi.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.dayuanzi.exception.ConfigException;
import cn.dayuanzi.util.YardUtils;

/**
 * 用户兴趣配置
 * 
 * @author : leihc
 * @date : 2015年4月16日 下午6:35:12
 * @version : 1.0
 */
public class InterestConf implements IConfig {
	
	private static Map<Integer, Interest> interests = new HashMap<Integer, Interest>();

	/**
	 * @see cn.dayuanzi.config.IConfig#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		Document docXml;
		try {
			SAXReader reader = new SAXReader();
			docXml = reader.read(YardUtils.getResourcesFile("interests.xml"));
			Element root = docXml.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				Integer id = Integer.parseInt(element.attributeValue("id"));
				String content = element.attributeValue("name");
				interests.put(id, new Interest(id,content));
			}
		} catch (DocumentException ex) {
			ex.printStackTrace();
			throw new ConfigException("interests.xml信息错误！");
		}
	}

	/**
	 * @see cn.dayuanzi.config.IConfig#reload()
	 */
	@Override
	public void reload() {
		// TODO Auto-generated method stub
		this.init();
	}

	public static Map<Integer, Interest> getInterests() {
		return interests;
	}

}
