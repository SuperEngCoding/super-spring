package com.customize.spring.factory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:  Bean 工厂
 *
 * @author SuperEngCoding
 * @create 2020-05-03 18:04
 */
public class BeanFactory {

    /**
     * 存放对象
     */
    private static Map<String, Object> map = new ConcurrentHashMap<>();


    /**
     * 对外提供的接口
     * @param id
     * @return
     */
    public static  Object getBean(String id) {
        return map.get(id);
    }




    static {
        // 只加载一次就是在BeanFactory初始化的时候去加载类
        // 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
        System.out.println("开始加载Bean对象");
        // 加载xml
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        // 解析xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            for (int i = 0; i < beanList.size(); i++) {
                Element element =  beanList.get(i);
                // 处理每个bean元素，获取到该元素的id 和 class 属性
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                // 通过反射技术实例化对象
                Class<?> aClass = Class.forName(clazz);
                Object o = aClass.newInstance();
                // 存储到map中待用
                map.put(id,o);
            }
            // 实例化完成之后维护对象的依赖关系，检查哪些对象需要传值进入，根据它的配置，我们传入相应的值
            // 有property子元素的bean就有传值需求
            List<Element> propertyList = rootElement.selectNodes("//property");
            // 解析property，获取父元素
            for (int i = 0; i < propertyList.size(); i++) {
                Element element =  propertyList.get(i);
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");

                // 找到当前需要被处理依赖关系的bean
                Element parent = element.getParent();
                // 调用父元素对象的反射功能
                String parentId = parent.attributeValue("id");
                Object parentObject = map.get(parentId);
                // 遍历父对象中的所有方法，找到"set" + name
                Method[] methods = parentObject.getClass().getMethods();
                for (int j = 0; j < methods.length; j++) {
                    Method method = methods[j];
                    // 该方法就是 setAccountDao(AccountDao accountDao)
                    if(method.getName().equalsIgnoreCase(name)) {
                        method.invoke(parentObject,map.get(ref));
                    }
                }
                // 把处理之后的parentObject重新放到map中
                map.put(parentId,parentObject);
            }
            System.out.println("加载完毕,Map中的Bean对象个数为:" + map.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}