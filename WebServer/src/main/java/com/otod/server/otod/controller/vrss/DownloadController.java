package com.otod.server.otod.controller.vrss;

import com.otod.server.otod.model.vrss.Download;
import com.otod.server.otod.services.vrss.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/vrss/Download")
public class DownloadController {
    @Autowired
    private DownloadService downloadService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Download> list(@RequestParam("user_id") Integer user_id){
        return downloadService.findDownload(user_id);
    }

    @GetMapping(value = "/download")
    public void download(HttpServletRequest req,HttpServletResponse res) {
        String webroot=System.getProperty("user.dir");
        String fileName = (String) req.getParameter("file_url");
        Integer  user_id= Integer.parseInt((String) req.getParameter("user_id"));
        Integer  file_id= Integer.parseInt((String) req.getParameter("file_id"));
        downloadService.addDownload(file_id,user_id);
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(webroot+fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
