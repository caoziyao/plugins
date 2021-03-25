package com.zel.commonutils;

import lombok.SneakyThrows;
import lombok.var;
import org.dom4j.DocumentException;
import org.ofdrw.converter.ConvertHelper;
import org.ofdrw.converter.GeneralConvertException;
import org.ofdrw.core.annotation.Annotations;
import org.ofdrw.core.annotation.pageannot.*;
import org.ofdrw.core.basicStructure.doc.Document;
import org.ofdrw.core.basicStructure.pageObj.layer.block.TextObject;
import org.ofdrw.core.basicType.ST_Box;
import org.ofdrw.core.basicType.ST_ID;
import org.ofdrw.core.basicType.ST_Loc;
import org.ofdrw.core.basicType.ST_RefID;
import org.ofdrw.core.text.TextCode;
import org.ofdrw.font.FontName;
import org.ofdrw.layout.OFDDoc;
import org.ofdrw.layout.VirtualPage;
import org.ofdrw.layout.edit.AdditionVPage;
import org.ofdrw.layout.edit.Annotation;
import org.ofdrw.layout.element.*;
import org.ofdrw.layout.element.canvas.Canvas;
import org.ofdrw.layout.element.canvas.FontSetting;
import org.ofdrw.pkg.container.DocDir;
import org.ofdrw.pkg.container.OFDDir;
import org.ofdrw.pkg.container.PageDir;
import org.ofdrw.reader.OFDReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class OFDDemo {

    public static void addWatermark() throws IOException, DocumentException {
        Path srcP = Paths.get("E:\\2.ofd");
        Path outP = Paths.get("E:\\3.ofd");
        try (OFDReader reader = new OFDReader(srcP);
             OFDDoc ofdDoc = new OFDDoc(reader, outP)) {

            AdditionVPage avPage = ofdDoc.getAVPage(1);
            Div e = new Div(10d, 10d)
                    .setPosition(Position.Absolute);

            avPage.add(e);

            //ofdDoc.set
            //String plaintext = "小时候\n" +
            //        "乡愁是一枚小小的邮票\n" +
            //        "我在这头\n" +
            //        "母亲在那头\n" +
            //        "\n" +
            //        "长大后\n" +
            //        "乡愁是一张窄窄的船票\n" +
            //        "我在这头\n" +
            //        "新娘在那头\n" +
            //        "\n" +
            //        "后来啊\n" +
            //        "乡愁是一方矮矮的坟墓\n" +
            //        "我在外头\n" +
            //        "母亲在里头\n" +
            //        "\n" +
            //        "而现在\n" +
            //        "乡愁是一湾浅浅的海峡\n" +
            //        "我在这头\n" +
            //        "大陆在那头\n";
            //Span titleContent = new Span("乡愁").setBold(true).setFontSize(13d).setLetterSpacing(10d);
            //Paragraph title = new Paragraph().add(titleContent);
            //title.setFloat(AFloat.center).setMarginBottom(5d);
            //ofdDoc.add(title);
            //final String[] txtCollect = plaintext.split("\\\n");
            //for (String txt : txtCollect) {
            //    Paragraph p = new Paragraph().setFontSize(4d)
            //            .setLineSpace(3d)
            //            .add(txt);
            //    ofdDoc.add(p);
            //}
        }
        System.out.println("生成文档位置：" + outP.toAbsolutePath().toString());
    }

    public static  void test() throws IOException {
        //Path path = Paths.get("E:\\032001900211_79450469.ofd");
// 1. 文件输入路径
        Path outP = Paths.get("E:\\2.ofd");


        //Path outP = Paths.get("target/Canvas-scale.ofd");
        try (OFDDoc ofdDoc = new OFDDoc(outP)) {
            VirtualPage vPage = new VirtualPage(ofdDoc.getPageLayout());

            Canvas canvas = new Canvas(200d, 200d);
            canvas.setPosition(Position.Absolute)
                    .setX(5d).setY(45d)
                    .setBorder(1d);

            canvas.setDrawer(ctx -> {
                //ctx.strokeRect(5, 5, 10, 6);
                ctx.scale(2, 2);
                //ctx.strokeRect(5, 5, 10, 6);
                //ctx.scale(2, 2);
                //ctx.strokeRect(5, 5, 10, 6);
                //ctx.scale(2, 2);
                //ctx.strokeRect(5, 5, 10, 6);
            });
            vPage.add(canvas);

            ofdDoc.addVPage(vPage);
        }
        System.out.println("生成文档位置：" + outP.toAbsolutePath().toString());
    }

    @SneakyThrows
    public static void main(String[] args) throws IOException {
        // 1. 文件输入路径
        File file = new File("E:\\2.ofd");
        Path src = Paths.get("E:\\2.ofd");
        // 2. 转换后文件输出位置
        Path dst = Paths.get("E:\\3.pdf");

        Path dst2 = Paths.get(file.getAbsolutePath());
        System.out.println(file.getName());
        //try (OFDDoc ofdDoc = new OFDDoc(src)) {
        //    //ofdDoc.setDefaultPageLayout()
        //    //ofdDoc.pa
        //}
        //try {
        //    // 3. OFD转换PDF
        //    ConvertHelper.toPdf(src, dst);
        //    System.out.println("生成文档位置: " + dst.toAbsolutePath());
        //} catch (GeneralConvertException e) {
        //    // GeneralConvertException 类型错误表明转换过程中发生异常
        //    e.printStackTrace();
        //}
    }

    //public static void main(String[] args) throws IOException {
    //    Path path = Paths.get("E:\\032001900211_79450469.ofd");
    //    //try (OFDDoc ofdDoc = new OFDDoc(path)) {
    //        //ofdDoc.
    //        //Paragraph p = new Paragraph("你好呀，OFD Reader&Writer！");
    //        //ofdDoc.add(p);
    //    }
    //    //System.out.println("生成文档位置: " + path.toAbsolutePath());
    //}
}
