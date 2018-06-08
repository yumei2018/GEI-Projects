package entity.core.controller.service;

import entity.core.util.WebUtil;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
@RequestMapping("/pdfservice")
public class PdfService extends MultiActionController {
  @RequestMapping("/htmltopdf")
  public void htmlToPdf(@RequestParam("html") String html
          , @RequestParam("name") String pdfname
          , HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      if (StringUtils.isEmpty(html)) {
        throw new Exception("The request parameter 'html' cannot be empty!");
      }
      try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
        WebUtil.toPdf(html, os);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + pdfname + ".pdf\"");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        os.writeTo(response.getOutputStream());
      }
    }
    catch (Exception exp) {
      response.setContentType(MediaType.TEXT_HTML_VALUE);
      response.sendError(response.SC_INTERNAL_SERVER_ERROR, exp.getMessage());
    }  
  }
}
