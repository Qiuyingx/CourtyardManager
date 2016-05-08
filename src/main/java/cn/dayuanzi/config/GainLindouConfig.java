package cn.dayuanzi.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.dayuanzi.util.YardUtils;

/**
 * 
 * @author : leihc
 * @date : 2015年6月8日
 * @version : 1.0
 */
public class GainLindouConfig implements IConfig {

	private final static Map<Integer, LindouInfo> datas = new HashMap<Integer, LindouInfo>();
	/**
	 * @see cn.dayuanzi.config.IConfig#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		Document docXml;
		try {
			SAXReader reader = new SAXReader();
			docXml = reader.read(YardUtils.getResourcesFile("gainLindou.xml"));
			Element root = docXml.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				LindouInfo info = new LindouInfo();
				Integer id = Integer.parseInt(element.attributeValue("id"));
				int everyTime = Integer.parseInt(element.attributeValue("lindou"));
				info.setId(id);
				info.setLindou(everyTime);
				info.setRemark(element.attributeValue("remark"));
				info.setImage(element.attributeValue("image"));
				info.setDec(element.attributeValue("dec"));
				datas.put(id, info);
			}
		} catch (DocumentException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * @see cn.dayuanzi.config.IConfig#reload()
	 */
	@Override
	public void reload() {
		datas.clear();
		this.init();
	}

	public static Map<Integer, LindouInfo> getDatas() {
		return datas;
	}

}
