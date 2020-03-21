<%@ page
import="java.awt.image.BufferedImage,java.awt.*,com.sun.image.codec.jpeg.*"%><%
/**
* 产后验证码的JSP
*/
// 宽度
int WIDTH = 45;
// 高度
int HIGHT = 30;
// 验证码长度
int LENGTH = 4;

BufferedImage image = new BufferedImage(WIDTH,HIGHT, BufferedImage.TYPE_INT_RGB);
// 获取图形上下文
Graphics g = image.getGraphics();
// 设定背景色
g.setColor(Color.white);
g.fillRect(0, 0, WIDTH,HIGHT);
//画边框
//g.setColor(Color.black);
//g.drawRect(0,0,WIDTH-1,HIGHT-1);

// 取随机产生的认证码(4位数字)
//String rand = request.getParameter("rand");
//rand = rand.substring(0,rand.indexOf("."));
String value = String.valueOf((int)((10^LENGTH)*Math.random()));
String rand = value;
switch(rand.length())
{
case 1: rand = "000"+rand; break;
case 2: rand = "00"+rand; break;
case 3: rand = "0"+rand; break;
default: rand = rand.substring(0,4); break;
}
session.setAttribute("rand", rand);

g.setColor(new Color((int)(200 * Math.random()), (int)(200 * Math.random()), (int)(200 * Math.random())));
g.setFont(new Font("Times New Roman",Font.PLAIN,16));
g.drawString(rand,4,20);

//生成干扰点
for (int i=0;i<WIDTH;i++)
{
g.setColor(new Color((int)(256 * Math.random()), (int)(256 * Math.random()), (int)(256 * Math.random())));
int y = (int)(HIGHT * Math.random());
g.drawLine(i,y,i,y);
}
g.dispose();

// 输出图象到页面
response.setContentType("image/JPEG; charset=UTF-8");
JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
encoder.encode(image);//对图片进行输出编码
%>