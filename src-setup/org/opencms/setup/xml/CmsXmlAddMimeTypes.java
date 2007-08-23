/*
 * File   : $Source: /alkacon/cvs/opencms/src-setup/org/opencms/setup/xml/CmsXmlAddMimeTypes.java,v $
 * Date   : $Date: 2007/08/22 11:11:45 $
 * Version: $Revision: 1.1 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (c) 2002 - 2007 Alkacon Software GmbH (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.setup.xml;

import org.opencms.configuration.CmsConfigurationManager;
import org.opencms.configuration.CmsVfsConfiguration;
import org.opencms.configuration.I_CmsXmlConfiguration;

import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

/**
 * Adds the new mime types node.
 * <p>
 * 
 * @author Michael Moossen
 * 
 * @version $Revision: 1.1 $
 * 
 * @since 6.2.3
 */
public class CmsXmlAddMimeTypes extends A_CmsSetupXmlUpdate {

    /** List of xpaths to update. */
    private List m_xpaths;

    /** List of mimetypes to add. */
    private String[][] m_mimeTypes = {
        {".ez", "application/andrew-inset"},
        {".mme", "application/base64"},
        {".boo", "application/book"},
        {".book", "application/book"},
        {".ccad", "application/clariscad"},
        {".dp", "application/commonground"},
        {".drw", "application/drafting"},
        {".xl", "application/excel"},
        {".frl", "application/freeloader"},
        {".vew", "application/groupwise"},
        {".hta", "application/hta"},
        {".unv", "application/i-deas"},
        {".inf", "application/inf"},
        {".hqx", "application/mac-binhex40"},
        {".cpt", "application/mac-compactpro"},
        {".mrc", "application/marc"},
        {".mbd", "application/mbedlet"},
        {".aps", "application/mime"},
        {".ppz", "application/mspowerpoint"},
        {".doc", "application/msword"},
        {".dot", "application/msword"},
        {".w6w", "application/msword"},
        {".wiz", "application/msword"},
        {".word", "application/msword"},
        {".mcp", "application/netmc"},
        {".a", "application/octet-stream"},
        {".arc", "application/octet-stream"},
        {".arj", "application/octet-stream"},
        {".bin", "application/octet-stream"},
        {".class", "application/octet-stream"},
        {".dll", "application/octet-stream"},
        {".dms", "application/octet-stream"},
        {".dump", "application/octet-stream"},
        {".exe", "application/octet-stream"},
        {".lha", "application/octet-stream"},
        {".lhx", "application/octet-stream"},
        {".lzh", "application/octet-stream"},
        {".o", "application/octet-stream"},
        {".psd", "application/octet-stream"},
        {".saveme", "application/octet-stream"},
        {".zoo", "application/octet-stream"},
        {".oda", "application/oda"},
        {".pdf", "application/pdf"},
        {".p7s", "application/pkcs7-signature"},
        {".crl", "application/pkix-crl"},
        {".pls", "application/pls"},
        {".ai", "application/postscript"},
        {".eps", "application/postscript"},
        {".ps", "application/postscript"},
        {".part", "application/pro_eng"},
        {".prt", "application/pro_eng"},
        {".set", "application/set"},
        {".smi", "application/smil"},
        {".smil", "application/smil"},
        {".sol", "application/solids"},
        {".sdr", "application/sounder"},
        {".step", "application/step"},
        {".stp", "application/step"},
        {".ssm", "application/streamingmedia"},
        {".vda", "application/vda"},
        {".kml", "application/vnd.google-earth.kml+xml"},
        {".kmz", "application/vnd.google-earth.kmz"},
        {".mif", "application/vnd.mif"},
        {".xls", "application/vnd.ms-excel"},
        {".pot", "application/vnd.ms-powerpoint"},
        {".ppt", "application/vnd.ms-powerpoint"},
        {".mpp", "application/vnd.ms-project"},
        {".odc", "application/vnd.oasis.opendocument.chart"},
        {".odb", "application/vnd.oasis.opendocument.database"},
        {".odf", "application/vnd.oasis.opendocument.formula"},
        {".odg", "application/vnd.oasis.opendocument.graphics"},
        {".otg", "application/vnd.oasis.opendocument.graphics-template"},
        {".odi", "application/vnd.oasis.opendocument.image"},
        {".odp", "application/vnd.oasis.opendocument.presentation"},
        {".otp", "application/vnd.oasis.opendocument.presentation-template"},
        {".ods", "application/vnd.oasis.opendocument.spreadsheet"},
        {".ots", "application/vnd.oasis.opendocument.spreadsheet-template"},
        {".odt", "application/vnd.oasis.opendocument.text"},
        {".odm", "application/vnd.oasis.opendocument.text-master"},
        {".ott", "application/vnd.oasis.opendocument.text-template ott"},
        {".oth", "application/vnd.oasis.opendocument.text-web"},
        {".sxc", "application/vnd.sun.xml.calc"},
        {".stc", "application/vnd.sun.xml.calc.template"},
        {".sxd", "application/vnd.sun.xml.draw"},
        {".std", "application/vnd.sun.xml.draw.template"},
        {".sxi", "application/vnd.sun.xml.impress"},
        {".sti", "application/vnd.sun.xml.impress.template"},
        {".sxm", "application/vnd.sun.xml.math"},
        {".sxw", "application/vnd.sun.xml.writer"},
        {".sxg", "application/vnd.sun.xml.writer.global"},
        {".stw", "application/vnd.sun.xml.writer.template"},
        {".fdf", "application/vndfdf"},
        {".hgl", "application/vndhp-hpgl"},
        {".hpg", "application/vndhp-hpgl"},
        {".hpgl", "application/vndhp-hpgl"},
        {".sst", "application/vndms-pkicertstore"},
        {".pko", "application/vndms-pkipko"},
        {".cat", "application/vndms-pkiseccat"},
        {".ppa", "application/vndms-powerpoint"},
        {".pps", "application/vndms-powerpoint"},
        {".pwz", "application/vndms-powerpoint"},
        {".ncm", "application/vndnokiaconfiguration-message"},
        {".rng", "application/vndnokiaringing-tone"},
        {".rnx", "application/vndrn-realplayer"},
        {".wmlc", "application/vndwapwmlc"},
        {".wmlsc", "application/vndwapwmlscriptc"},
        {".web", "application/vndxara"},
        {".vmd", "application/vocaltec-media-desc"},
        {".vmf", "application/vocaltec-media-file"},
        {".wp", "application/wordperfect"},
        {".wp6", "application/wordperfect"},
        {".w60", "application/wordperfect60"},
        {".wp5", "application/wordperfect60"},
        {".w61", "application/wordperfect61"},
        {".wk1", "application/x-123"},
        {".aim", "application/x-aim"},
        {".aab", "application/x-authorware-bin"},
        {".aam", "application/x-authorware-map"},
        {".aas", "application/x-authorware-seg"},
        {".bcpio", "application/x-bcpio"},
        {".bsh", "application/x-bsh"},
        {".pyc", "application/x-bytecodepython"},
        {".bz", "application/x-bzip"},
        {".boz", "application/x-bzip2"},
        {".bz2", "application/x-bzip2"},
        {".vcd", "application/x-cdlink"},
        {".cha", "application/x-chat"},
        {".chat", "application/x-chat"},
        {".pgn", "application/x-chess-pgn"},
        {".cco", "application/x-cocoa"},
        {".tgz", "application/x-compressed"},
        {".z", "application/x-compressed"},
        {".nsc", "application/x-conference"},
        {".cpio", "application/x-cpio"},
        {".csh", "application/x-csh"},
        {".deepv", "application/x-deepv"},
        {".dcr", "application/x-director"},
        {".dir", "application/x-director"},
        {".dxr", "application/x-director"},
        {".dvi", "application/x-dvi"},
        {".elc", "application/x-elc"},
        {".env", "application/x-envoy"},
        {".evy", "application/x-envoy"},
        {".es", "application/x-esrehber"},
        {".xlb", "application/x-excel"},
        {".xlc", "application/x-excel"},
        {".xld", "application/x-excel"},
        {".xlk", "application/x-excel"},
        {".xll", "application/x-excel"},
        {".xlm", "application/x-excel"},
        {".xlt", "application/x-excel"},
        {".xlv", "application/x-excel"},
        {".pre", "application/x-freelance"},
        {".spl", "application/x-futuresplash"},
        {".gsp", "application/x-gsp"},
        {".gss", "application/x-gss"},
        {".gtar", "application/x-gtar"},
        {".gz", "application/x-gzip"},
        {".hdf", "application/x-hdf"},
        {".help", "application/x-helpfile"},
        {".imap", "application/x-httpd-imap"},
        {".ima", "application/x-ima"},
        {".ins", "application/x-internett-signup"},
        {".iv", "application/x-inventor"},
        {".ip", "application/x-ip2"},
        {".jcm", "application/x-java-commerce"},
        {".jnlp", "application/x-java-jnlp-file"},
        {".js", "application/x-javascript"},
        {".skd", "application/x-koan"},
        {".skm", "application/x-koan"},
        {".skp", "application/x-koan"},
        {".skt", "application/x-koan"},
        {".latex", "application/x-latex"},
        {".ltx", "application/x-latex"},
        {".ivy", "application/x-livescreen"},
        {".wq1", "application/x-lotus"},
        {".lzx", "application/x-lzx"},
        {".mc$", "application/x-magic-cap-package-10"},
        {".mcd", "application/x-mathcad"},
        {".mm", "application/x-meme"},
        {".nix", "application/x-mix-transfer"},
        {".asx", "application/x-mplayer2"},
        {".xla", "application/x-msexcel"},
        {".xlw", "application/x-msexcel"},
        {".ani", "application/x-navi-animation"},
        {".nvd", "application/x-navidoc"},
        {".map", "application/x-navimap"},
        {".stl", "application/x-navistyle"},
        {".cdf", "application/x-netcdf"},
        {".nc", "application/x-netcdf"},
        {".pkg", "application/x-newton-compatible-pkg"},
        {".aos", "application/x-nokia-9000-communicator-add-on-software"},
        {".msi", "application/x-ole-storage"},
        {".omc", "application/x-omc"},
        {".omcd", "application/x-omcdatamaker"},
        {".omcr", "application/x-omcregerator"},
        {".pm4", "application/x-pagemaker"},
        {".pm5", "application/x-pagemaker"},
        {".pcl", "application/x-pcl"},
        {".plx", "application/x-pixclscript"},
        {".p10", "application/x-pkcs10"},
        {".p12", "application/x-pkcs12"},
        {".p7r", "application/x-pkcs7-certreqresp"},
        {".p7c", "application/x-pkcs7-mime"},
        {".p7m", "application/x-pkcs7-mime"},
        {".p7a", "application/x-pkcs7-signature"},
        {".mpc", "application/x-project"},
        {".mpt", "application/x-project"},
        {".mpv", "application/x-project"},
        {".mpx", "application/x-project"},
        {".wb1", "application/x-qpro"},
        {".sdp", "application/x-sdp"},
        {".sea", "application/x-sea"},
        {".sl", "application/x-seelogo"},
        {".sh", "application/x-sh"},
        {".shar", "application/x-shar"},
        {".swf", "application/x-shockwave-flash"},
        {".spr", "application/x-sprite"},
        {".sprite", "application/x-sprite"},
        {".sit", "application/x-stuffit"},
        {".sv4cpio", "application/x-sv4cpio"},
        {".sv4crc", "application/x-sv4crc"},
        {".tar", "application/x-tar"},
        {".sbk", "application/x-tbook"},
        {".tbk", "application/x-tbook"},
        {".tcl", "application/x-tcl"},
        {".tex", "application/x-tex"},
        {".texi", "application/x-texinfo"},
        {".texinfo", "application/x-texinfo"},
        {".roff", "application/x-troff"},
        {".t", "application/x-troff"},
        {".tr", "application/x-troff"},
        {".man", "application/x-troff-man"},
        {".me", "application/x-troff-me"},
        {".ms", "application/x-troff-ms"},
        {".ustar", "application/x-ustar"},
        {".vsd", "application/x-visio"},
        {".vst", "application/x-visio"},
        {".vsw", "application/x-visio"},
        {".mzz", "application/x-vndaudioexplosionmzz"},
        {".xpix", "application/x-vndls-xpix"},
        {".src", "application/x-wais-source"},
        {".wsrc", "application/x-wais-source"},
        {".hlp", "application/x-winhelp"},
        {".wtk", "application/x-wintalk"},
        {".wpd", "application/x-wpwin"},
        {".wri", "application/x-wri"},
        {".cer", "application/x-x509-ca-cert"},
        {".der", "application/x-x509-ca-cert"},
        {".crt", "application/x-x509-user-cert"},
        {".dtd", "application/xml-dtd"},
        {".zip", "application/zip"},
        {".au", "audio/basic"},
        {".snd", "audio/basic"},
        {".it", "audio/it"},
        {".funk", "audio/make"},
        {".my", "audio/make"},
        {".pfunk", "audio/makemyfunk"},
        {".rmi", "audio/mid"},
        {".kar", "audio/midi"},
        {".mid", "audio/midi"},
        {".midi", "audio/midi"},
        {".m2a", "audio/mpeg"},
        {".mp2", "audio/mpeg"},
        {".mp3", "audio/mpeg"},
        {".mpga", "audio/mpeg"},
        {".s3m", "audio/s3m"},
        {".tsi", "audio/tsp-audio"},
        {".tsp", "audio/tsplayer"},
        {".qcp", "audio/vndqcelp"},
        {".vox", "audio/voxware"},
        {".aif", "audio/x-aiff"},
        {".aifc", "audio/x-aiff"},
        {".aiff", "audio/x-aiff"},
        {".gsd", "audio/x-gsm"},
        {".gsm", "audio/x-gsm"},
        {".jam", "audio/x-jam"},
        {".lam", "audio/x-liveaudio"},
        {".mod", "audio/x-mod"},
        {".m3u", "audio/x-mpegurl"},
        {".wma", "audio/x-ms-wma"},
        {".la", "audio/x-nspaudio"},
        {".lma", "audio/x-nspaudio"},
        {".ram", "audio/x-pn-realaudio"},
        {".rm", "audio/x-pn-realaudio"},
        {".rmm", "audio/x-pn-realaudio"},
        {".rmp", "audio/x-pn-realaudio-plugin"},
        {".rpm", "audio/x-pn-realaudio-plugin"},
        {".sid", "audio/x-psid"},
        {".ra", "audio/x-realaudio"},
        {".vqf", "audio/x-twinvq"},
        {".vqe", "audio/x-twinvq-plugin"},
        {".vql", "audio/x-twinvq-plugin"},
        {".mjf", "audio/x-vndaudioexplosionmjuicemediafile"},
        {".voc", "audio/x-voc"},
        {".wav", "audio/x-wav"},
        {".xm", "audio/xm"},
        {".pdb", "chemical/x-pdb"},
        {".xyz", "chemical/x-pdb"},
        {".ivr", "i-world/i-vrml"},
        {".bm", "image/bmp"},
        {".bmp", "image/bmp"},
        {".rast", "image/cmu-raster"},
        {".fif", "image/fif"},
        {".flo", "image/florian"},
        {".turbot", "image/florian"},
        {".g3", "image/g3fax"},
        {".gif", "image/gif"},
        {".ief", "image/ief"},
        {".iefs", "image/ief"},
        {".jfif-tbnl", "image/jpeg"},
        {".jpe", "image/jpeg"},
        {".jpeg", "image/jpeg"},
        {".jpg", "image/jpeg"},
        {".jut", "image/jutvision"},
        {".nap", "image/naplps"},
        {".naplps", "image/naplps"},
        {".pic", "image/pict"},
        {".pict", "image/pict"},
        {".jfif", "image/pjpeg"},
        {".png", "image/png"},
        {".x-png", "image/png"},
        {".svg", "image/svg+xml"},
        {".tif", "image/tiff"},
        {".tiff", "image/tiff"},
        {".wbmp", "image/vnd.wap.wbmp"},
        {".fpx", "image/vndnet-fpx"},
        {".rf", "image/vndrn-realflash"},
        {".rp", "image/vndrn-realpix"},
        {".xif", "image/vndxiff"},
        {".ras", "image/x-cmu-raster"},
        {".dwg", "image/x-dwg"},
        {".dxf", "image/x-dwg"},
        {".svf", "image/x-dwg"},
        {".ico", "image/x-icon"},
        {".art", "image/x-jg"},
        {".jps", "image/x-jps"},
        {".nif", "image/x-niff"},
        {".niff", "image/x-niff"},
        {".pcx", "image/x-pcx"},
        {".pct", "image/x-pict"},
        {".pnm", "image/x-portable-anymap"},
        {".pbm", "image/x-portable-bitmap"},
        {".pgm", "image/x-portable-graymap"},
        {".ppm", "image/x-portable-pixmap"},
        {".qif", "image/x-quicktime"},
        {".qti", "image/x-quicktime"},
        {".qtif", "image/x-quicktime"},
        {".rgb", "image/x-rgb"},
        {".xbm", "image/x-xbitmap"},
        {".xpm", "image/x-xpixmap"},
        {".xwd", "image/x-xwindowdump"},
        {".mht", "message/rfc822"},
        {".mhtml", "message/rfc822"},
        {".iges", "model/iges"},
        {".igs", "model/iges"},
        {".mesh", "model/mesh"},
        {".msh", "model/mesh"},
        {".silo", "model/mesh"},
        {".dwf", "model/vnddwf"},
        {".vrml", "model/vrml"},
        {".wrl", "model/vrml"},
        {".pov", "model/x-pov"},
        {".gzip", "multipart/x-gzip"},
        {".pvu", "paleovu/x-pv"},
        {".asp", "text/asp"},
        {".csv", "text/comma-separated-values"},
        {".css", "text/css"},
        {".acgi", "text/html"},
        {".htm", "text/html"},
        {".html", "text/html"},
        {".htmls", "text/html"},
        {".htx", "text/html"},
        {".mcf", "text/mcf"},
        {".pas", "text/pascal"},
        {".asc", "text/plain"},
        {".c++", "text/plain"},
        {".com", "text/plain"},
        {".conf", "text/plain"},
        {".cxx", "text/plain"},
        {".def", "text/plain"},
        {".g", "text/plain"},
        {".idc", "text/plain"},
        {".list", "text/plain"},
        {".log", "text/plain"},
        {".lst", "text/plain"},
        {".mar", "text/plain"},
        {".sdml", "text/plain"},
        {".text", "text/plain"},
        {".txt", "text/plain"},
        {".rtx", "text/richtext"},
        {".rtf", "text/rtf"},
        {".wsc", "text/scriplet"},
        {".sgm", "text/sgml"},
        {".sgml", "text/sgml"},
        {".tsv", "text/tab-separated-values"},
        {".uni", "text/uri-list"},
        {".unis", "text/uri-list"},
        {".uri", "text/uri-list"},
        {".uris", "text/uri-list"},
        {".wml", "text/vnd.wap.wml"},
        {".abc", "text/vndabc"},
        {".flx", "text/vndfmiflexstor"},
        {".rt", "text/vndrn-realtext"},
        {".wmls", "text/vndwapwmlscript"},
        {".htt", "text/webviewhtml"},
        {".asm", "text/x-asm"},
        {".s", "text/x-asm"},
        {".aip", "text/x-audiosoft-intra"},
        {".c", "text/x-c"},
        {".cc", "text/x-c"},
        {".cpp", "text/x-c"},
        {".htc", "text/x-component"},
        {".f", "text/x-fortran"},
        {".f77", "text/x-fortran"},
        {".f90", "text/x-fortran"},
        {".for", "text/x-fortran"},
        {".h", "text/x-h"},
        {".hh", "text/x-h"},
        {".jav", "text/x-java-source"},
        {".java", "text/x-java-source"},
        {".lsx", "text/x-la-asf"},
        {".m", "text/x-m"},
        {".p", "text/x-pascal"},
        {".hlb", "text/x-script"},
        {".el", "text/x-scriptelisp"},
        {".ksh", "text/x-scriptksh"},
        {".lsp", "text/x-scriptlisp"},
        {".pl", "text/x-scriptperl"},
        {".pm", "text/x-scriptperl-module"},
        {".py", "text/x-scriptphyton"},
        {".rexx", "text/x-scriptrexx"},
        {".tcsh", "text/x-scripttcsh"},
        {".zsh", "text/x-scriptzsh"},
        {".shtml", "text/x-server-parsed-html"},
        {".ssi", "text/x-server-parsed-html"},
        {".etx", "text/x-setext"},
        {".spc", "text/x-speech"},
        {".talk", "text/x-speech"},
        {".uil", "text/x-uil"},
        {".uu", "text/x-uuencode"},
        {".uue", "text/x-uuencode"},
        {".vcs", "text/x-vcalendar"},
        {".xml", "text/xml"},
        {".xsd", "text/xml"},
        {".xsl", "text/xml"},
        {".afl", "video/animaflex"},
        {".avs", "video/avs-video"},
        {".m1v", "video/mpeg"},
        {".m2v", "video/mpeg"},
        {".mpa", "video/mpeg"},
        {".mpe", "video/mpeg"},
        {".mpeg", "video/mpeg"},
        {".mpg", "video/mpeg"},
        {".moov", "video/quicktime"},
        {".mov", "video/quicktime"},
        {".qt", "video/quicktime"},
        {".vdo", "video/vdo"},
        {".rv", "video/vndrn-realvideo"},
        {".viv", "video/vndvivo"},
        {".vivo", "video/vndvivo"},
        {".vos", "video/vosaic"},
        {".xdr", "video/x-amt-demorun"},
        {".xsr", "video/x-amt-showrun"},
        {".fmf", "video/x-atomic3d-feature"},
        {".dl", "video/x-dl"},
        {".dif", "video/x-dv"},
        {".dv", "video/x-dv"},
        {".fli", "video/x-fli"},
        {".gl", "video/x-gl"},
        {".isu", "video/x-isvideo"},
        {".mjpg", "video/x-motion-jpeg"},
        {".asf", "video/x-ms-asf"},
        {".wmv", "video/x-ms-wmv"},
        {".avi", "video/x-msvideo"},
        {".qtc", "video/x-qtc"},
        {".scm", "video/x-scm"},
        {".movie", "video/x-sgi-movie"},
        {".mv", "video/x-sgi-movie"},
        {".wmf", "windows/metafile"},
        {".mime", "www/mime"},
        {".ice", "x-conference/x-cooltalk"},
        {".3dm", "x-world/x-3dmf"},
        {".3dmf", "x-world/x-3dmf"},
        {".qd3", "x-world/x-3dmf"},
        {".qd3d", "x-world/x-3dmf"},
        {".svr", "x-world/x-svr"},
        {".wrz", "x-world/x-vrml"},
        {".vrt", "x-world/x-vrt"},
        {".xgz", "xgl/drawing"},
        {".xmz", "xgl/movie"}};

    /**
     * @see org.opencms.setup.xml.I_CmsSetupXmlUpdate#getName()
     */
    public String getName() {

        return "Add mime types";
    }

    /**
     * @see org.opencms.setup.xml.I_CmsSetupXmlUpdate#getXmlFilename()
     */
    public String getXmlFilename() {

        return CmsVfsConfiguration.DEFAULT_XML_FILE_NAME;
    }

    /**
     * @see org.opencms.setup.xml.A_CmsSetupXmlUpdate#executeUpdate(org.dom4j.Document,
     *      java.lang.String)
     */
    protected boolean executeUpdate(Document document, String xpath) {

        Node node = document.selectSingleNode(xpath);
        if (node == null) {
            if (xpath.equals(getXPathsToUpdate().get(0))) {
                for (int i = 0; i < m_mimeTypes.length; i++) {
                    String mPath = xpath
                        + "/"
                        + CmsVfsConfiguration.N_MIMETYPE
                        + "[@"
                        + CmsVfsConfiguration.A_EXTENSION
                        + "=\""
                        + m_mimeTypes[i][0]
                        + "\"]";
                    CmsSetupXmlHelper.setValue(
                        document,
                        mPath + "/@" + CmsVfsConfiguration.A_EXTENSION,
                        m_mimeTypes[i][0]);
                    CmsSetupXmlHelper.setValue(
                        document,
                        mPath + "/@" + I_CmsXmlConfiguration.A_TYPE,
                        m_mimeTypes[i][1]);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * @see org.opencms.setup.xml.A_CmsSetupXmlUpdate#getCommonPath()
     */
    protected String getCommonPath() {

        // /opencms/vfs/resources/mimetypes
        return new StringBuffer("/").append(CmsConfigurationManager.N_ROOT).append("/").append(
            CmsVfsConfiguration.N_VFS).append("/").append(CmsVfsConfiguration.N_RESOURCES).append("/").append(
            CmsVfsConfiguration.N_MIMETYPES).toString();
    }

    /**
     * @see org.opencms.setup.xml.A_CmsSetupXmlUpdate#getXPathsToUpdate()
     */
    protected List getXPathsToUpdate() {

        if (m_xpaths == null) {
            // /opencms/vfs/resources/mimetypes
            m_xpaths = Collections.singletonList(getCommonPath());
        }
        return m_xpaths;
    }

}