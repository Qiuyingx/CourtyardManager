/**
 * 
 */
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
 * @ClassName: invitationConf 
 * @Description: TODO
 * @author qiuyingxiang
 * @date 2015年6月2日 下午11:03:55 
 *  
 */

public class InvitationConfig implements IConfig {

	private static Map<Integer, InvitationInfo> invitations = new HashMap<Integer, InvitationInfo>();

	/**
	 * @see cn.dayuanzi.config.IConfig#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		Document docXml;
		try {
			SAXReader reader = new SAXReader();
			docXml = reader.read(YardUtils.getResourcesFile("invitations.xml"));
			Element root = docXml.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				Integer id = Integer.parseInt(element.attributeValue("id"));
				String invitation = element.attributeValue("name");
				invitations.put(id, new InvitationInfo(id,invitation));
			}
		} catch (DocumentException ex) {
			ex.printStackTrace();
			throw new ConfigException("invitations.xml信息错误！");
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

	public static Map<Integer, InvitationInfo> getInterests() {
		return invitations;
	}

}
