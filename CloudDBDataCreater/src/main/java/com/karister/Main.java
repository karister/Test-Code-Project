package com.karister;

import cn.hutool.core.util.RandomUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.HashSet;
import java.util.Set;


/**
 * @author karister
 * @create 2021-11-28 10:29
 */
public class Main {
    //{"_id":"001","address":"A区1栋001","area":"中心市场","brand":"骏鑫家具"}
    public static String[] areas = {"中心市场","博览中心","家私城","光明ABC区"};
    public static double[] latitude = {25.688255,25.685532,25.691644,25.685177};
    public static double[] longitude = {114.779696,114.779386,114.792854,114.785399};
    public static String[] brands = {"骏","鑫","昌","世","纪","飞","扬","伊","利","斯","达","玉","清","水","英","宝","富","鸿","星","尔","克","匡","威","星","光","大","道","颐","品","天","成","华","轩","华","美","祥","瑞"};
    public static Format f = new DecimalFormat("000");//将1变为001
    public static Set<String> addressSet = new HashSet<>();//address Set集合，防止生成重复的地址
    public static Set<String> brandSet = new HashSet<>();//brand Set集合，防止生成重复的品牌名
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        File file = new File("D:/test.txt");
        String fileIDStart = "cloud://cloud-env-9ggt1lz9119de7b5.636c-cloud-env-9ggt1lz9119de7b5-1308852391";
        String imgFileName = "test_img";
        String imgFormat = "jpg";
        for (int i = 1; i <= 500; i++) {
//            BrandImgEntity Data Create
//            String id = f.format(i);// "id"
//            String imgUrl = fileIDStart + '/' + imgFileName + '/' + RandomUtil.randomInt(1, 65) + '.' + imgFormat;
//            BrandImgEntity entity = new BrandImgEntity(id, imgUrl);
//            String str = mapper.writeValueAsString(entity) + "\n";
//            System.out.println(str);
//            BufferStringFlume.write(file,str);


//            Entity Data Create
            String openid = f.format(i);// "_id"
            String brand = createBrand();
            String name = createName();
            String phone = createPhone();
            String area = RandomUtil.randomEle(areas);
            String address = createAddress();
            double latitude = 0;
            double longitude = 0;
            for (int index = 0; index < areas.length; index++) {
                if(area.equals(Main.areas[index])) {
                    latitude = Main.latitude[index];
                    longitude = Main.longitude[index];
                }

            }
            String[] label = createLabel();
            String BrandImgSrc = fileIDStart + '/' + imgFileName + '/' + RandomUtil.randomInt(1, 65) + '.' + imgFormat;
            Integer browseNum = RandomUtil.randomInt(100, 3000);
            Integer authState = RandomUtil.randomInt(0, 2);
            String[] authImgUrl = {"",""};
            if(authState == 1) {
                authImgUrl[0] = BrandImgSrc;
                authImgUrl[1] = BrandImgSrc;
            }
            Integer viewState = 1;
            Integer notTest = 0;
            Entity entity = new Entity(openid,brand,name,phone,area,address,latitude,longitude,label,BrandImgSrc,browseNum,authState,authImgUrl,viewState,notTest);
            String str = mapper.writeValueAsString(entity) + "\n";
            System.out.println(str);
            BufferStringFlume.write(file,str);
        }



    }

    public static String createAddress () {
        String[] area_ABC = {"A","B","C","D","E"};
        String address = "";
        do {
            String add_a = RandomUtil.randomEle(area_ABC) + "区";
            String add_b = RandomUtil.randomInt(1, 6) + "栋";
            String add_c = f.format(RandomUtil.randomInt(1, 100));
            address = add_a + add_b + add_c;
        } while (!addressSet.add(address));// address已经存在
        return address;
    }

    public static String createBrand () {
        String brand = "";
        do {
            brand = "";
            // 随机生产2-4位组成的品牌
            for (int j = 0; j < RandomUtil.randomInt(2, 5); j++) {
                brand += RandomUtil.randomEle(brands);
            }
            brand += "家具";
        } while (!brandSet.add(brand));// brand已经存在
        return brand;
    }

    public static String createName () {
        String brand = "";
        do {
            brand = "";
            // 随机生产2-4位组成的品牌
            for (int j = 0; j < RandomUtil.randomInt(2, 3); j++) {
                brand += RandomUtil.randomEle(brands);
            }
        } while (!brandSet.add(brand));// brand已经存在
        return brand;
    }

    public static String createPhone () {
        String phone = "";
        String[] phoneStart = {"135","157","139","159","152","158","172","153"};
        phone = RandomUtil.randomEle(phoneStart) + RandomUtil.randomNumbers(8);
        return phone;
    }

    public static String[] createLabel () {
        Set<String> labelSet = new HashSet<>();
        String[] labels = {"实木家具","软体家具","五金家具","白胚家具","配套市场"};
        Integer labelNum = RandomUtil.randomInt(2, 5);
        String[] label = new String[labelNum];
        for (int i = 0; i < labelNum; i++) {
            do {
                label[i] = RandomUtil.randomEle(labels);
            } while (!labelSet.add(label[i]));// label已经重复
        }
        return label;
    }
}
