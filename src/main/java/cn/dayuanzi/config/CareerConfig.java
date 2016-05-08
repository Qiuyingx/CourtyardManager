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
 * 
 * @author : leihc
 * @date : 2015年5月6日
 * @version : 1.0
 */
public class CareerConfig implements IConfig {

	private static Map<Integer, Career> careers = new HashMap<Integer, Career>();
	
	/**
	 * @see cn.dayuanzi.config.IConfig#init()
	 */
	@Override
	public void init() {
		Document xmlDoc;
		try {
			SAXReader reader = new SAXReader();
			xmlDoc = reader.read(YardUtils.getResourcesFile("careers.xml"));
			Element root = xmlDoc.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				Integer id = Integer.parseInt(element.attributeValue("id"));
				String industry = element.attributeValue("industry");
				String domain = element.attributeValue("domain");
				Career career = new Career();
				career.setId(id);
				career.setIndustry(industry);
				career.setDomain(domain);
				careers.put(id, career);
			}
		}catch(DocumentException ex){
			ex.printStackTrace();
			throw new ConfigException("careers.xml信息错误！");
		}
	}

	/**
	 * @see cn.dayuanzi.config.IConfig#reload()
	 */
	@Override
	public void reload() {
		this.init();
	}

	public static Map<Integer, Career> getCareers() {
		return careers;
	}

}
