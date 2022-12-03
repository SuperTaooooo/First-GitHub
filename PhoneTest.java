package XML.phone;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;


public class PhoneTest {
    private Document document;
    public static void main(String[] args) {
        PhoneTest phoneTest = new PhoneTest();
        phoneTest.create();
        phoneTest.show();
        phoneTest.add();
        phoneTest.update();
        phoneTest.delete();

    }

    //创建document对象
   public void create(){
       //创建document对象
       SAXReader saxReader = new SAXReader();
       try {
           document = saxReader.read("src/XML/phone/收藏信息3.xml");
       } catch (DocumentException e) {
           e.printStackTrace();
       }
   }

   //展示信息
    public void show(){
        Element element = document.getRootElement();
        //用迭代器遍历
        Iterator it = element.elementIterator();
        while(it.hasNext()){
            Element element1 = (Element) it.next();
            System.out.println(element1.attributeValue("name"));
            Iterator it1 = element1.elementIterator();
            while(it1.hasNext()){
                Element element2 = (Element) it1.next();
                System.out.println(element2.attributeValue("name"));
            }
        }
    }

    //新增信息
    public void add(){
        Element element = document.getRootElement();
        Element element1 = element.addElement("Brand");
        element1.addAttribute("id","中国");
        element1.addAttribute("name","三星");
        Element element2 = element1.addElement("Type");
        element2.addAttribute("name","note4");
        save();
    }

    //保存信息
    public void save(){
        XMLWriter xmlWriter = null;
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("UTF-8");
        try {
            xmlWriter = new XMLWriter(new FileWriter("src/XML/phone/收藏信息3.xml"));
            xmlWriter.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                xmlWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //修改信息
    public void update(){
        Element element = document.getRootElement();
        Iterator it = element.elementIterator();
        while(it.hasNext()){
            Element element1 = (Element) it.next();
            if(element1.attributeValue("name").equals("三星")){
                element1.addAttribute("id","韩国");
            }
        }
        save();
    }

    //删除信息
    public void delete(){
        Element element = document.getRootElement();
        Iterator it = element.elementIterator();
        while(it.hasNext()){
            Element element1 = (Element) it.next();
            if(element1.attributeValue("name").equals("三星")){
                element1.getParent().remove(element1);
            }
        }
    }
}
