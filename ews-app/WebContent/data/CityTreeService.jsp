<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.corp.basic.Constant,java.io.BufferedReader,java.io.FileInputStream,java.io.InputStreamReader,java.lang.reflect.Method" %>
<%
    request.setCharacterEncoding("UTF-8");
//    response.setCharacterEncoding("UTF-8");

    String methodName = request.getParameter("method");

    Class[] argsClass = new Class[2];
    argsClass[0] = HttpServletRequest.class;
    argsClass[1] = HttpServletResponse.class;

    Class cls = this.getClass();
    Method method = cls.getMethod(methodName, argsClass);

    Object[] args = new Object[2];
    args[0] = request;
    args[1] = response;
    method.invoke(this, args);
%>
<%!
    public void LoadNodes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String sfile = "", fileName = "";
            fileName = "citytree.txt";
            if (!Constant.isSystemDebug()) {//生产环境
                sfile = "D:\\eps\\eps-app\\data\\" + fileName;
            } else {//测试环境
                sfile = "D:\\ywd\\express\\data\\" + fileName;
            }
            InputStreamReader isr = null;
            try {
                isr = new InputStreamReader(new FileInputStream(sfile), "UTF-8");
            } catch (Exception e) {
                if (!Constant.isSystemDebug()) {//生产环境
                    sfile = "D:\\eps\\eps-app\\data\\" + fileName;
                } else {//测试环境
                    sfile = "D:\\ywd\\express\\data\\" + fileName;
                }
                isr = new InputStreamReader(new FileInputStream(sfile), "UTF-8");
            }

            BufferedReader br = new BufferedReader(isr);
            String data = "";
            data = br.readLine();
            br.close();
            isr.close();

            response.getWriter().write(data);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
%>