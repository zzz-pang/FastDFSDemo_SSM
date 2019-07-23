package com.itqf.controller;

import com.itqf.pojo.HouseInfo;
import com.itqf.service.HouseInfoService;
import com.itqf.utils.FastDFSUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Company: 千锋互联
 * @Author: 李丽婷
 * @Date: 2019/7/22
 * @Time: 上午11:01
 */
@Controller
public class MyController {


    @Resource
    private HouseInfoService houseInfoService;


    @RequestMapping("/upload.json")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){//MultipartFile
        try{

            //1,怎么得到名称
            String name = file.getOriginalFilename();//dnhiwhfiwfihw.jpg
            //2,后缀
            String suffix =  name.substring(name.lastIndexOf(".")+1);

            //3,得到上传的文件的byte数组
            byte[]b =   file.getBytes();

            FastDFSUtils fastDFSUtils = new FastDFSUtils();

            String s [] =  fastDFSUtils.upload(b,suffix);

            StringBuilder stringBuilder = new StringBuilder("http://192.168.82.88:82/");
            if (s!=null){
                //  group1
                //  M00/00/00/dnhqihqi.jpg
                for (int i = 0; i < s.length; i++) {
                    stringBuilder.append(s[i]);
                    if (i==0){
                        stringBuilder.append("/");
                    }

                }
            }


            return  stringBuilder.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  null;

    }



    @RequestMapping("/fileupload.json")
    @ResponseBody
    public Map<String,Object> fileupload(@RequestParam("file") MultipartFile file){//MultipartFile
        Map<String,Object> map = new HashMap<>();

        try{
            //1,怎么得到名称
            String name = file.getOriginalFilename();//dnhiwhfiwfihw.jpg
            //2,后缀
            String suffix =  name.substring(name.lastIndexOf(".")+1);

            //3,得到上传的文件的byte数组
            byte[]b =   file.getBytes();

            FastDFSUtils fastDFSUtils = new FastDFSUtils();

            String s [] =  fastDFSUtils.upload(b,suffix);

            StringBuilder stringBuilder = new StringBuilder("http://192.168.82.88:82/");
            if (s!=null){
                //  group1
                //  M00/00/00/dnhqihqi.jpg
                for (int i = 0; i < s.length; i++) {
                    stringBuilder.append(s[i]);
                    if (i==0){
                        stringBuilder.append("/");
                    }

                }
            }

            String url  =   stringBuilder.toString();
            map.put("status",200);
            map.put("msg","success");
            map.put("url",url);
            return  map;

        }catch(Exception e){
            e.printStackTrace();
        }
        map.put("status",500);
        map.put("msg","fail");

        return  map;

    }

    @RequestMapping("/addHouseInfo.json")
    @ResponseBody
    public    Map<String,Object>  save(HouseInfo info){
        Map<String,Object> map = new HashMap<>();

       boolean f =  houseInfoService.saveHouseInfo(info);
       if (f){
           map.put("status","200");
           map.put("msg","success");
       }else{
           map.put("msg","fail");
           map.put("status","500");
       }

       return map;
    }




}
