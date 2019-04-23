package com.zsd.tools;


import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class AuthImg extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//��������ͼ����֤��������ĸ������ʹ�С
	public AuthImg() {
		super();
	}
    //servlet����Ӧ����
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	System.out.println(String.valueOf(request.getParameter("userId")));
    	//������Ӧ���ļ�ͷ
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        //�������ɵ���Ӧ��ͼƬ������JSPҳ��
        response.setContentType("image/jpeg");
      //��֤�룬��2��һλ���ļӼ�������
        Integer num1 = (int)(Math.random() * 10);
        Integer num2 = (int)(Math.random() * 10);
        String verifyCode = "";
        String funMethod = "��";
        Integer result = 0;
        Random random = new Random();
        Integer funNo = random.nextInt(3);//������0,1,2֮������������
        if(funNo.equals(2)){//�˷�
        	result = num1 * num2;
        	funMethod = "��";
        	verifyCode = num1 + funMethod + num2 + "�ȼ�";
        }else{
            if(num1 >= num2){
            	funMethod = "��";
            	result = num1 - num2;
            }else{
            	result = num1 + num2;
            }
        	verifyCode = num1 + funMethod + num2 + "�ȼ�";
        }
    	
        String finalResult = String.valueOf(result);
        //������������ַ�������session��
        HttpSession session = request.getSession(false);
        session.setAttribute("rand",finalResult);
      //��ͼƬ�����servlet��Ӧ
        VerifyCodeUtils.outputImage(150, 40, response.getOutputStream(), verifyCode);
        
    }
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		super.init();
	}

}
