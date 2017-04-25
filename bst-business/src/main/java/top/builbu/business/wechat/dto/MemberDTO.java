package top.builbu.business.wechat.dto;  

import java.util.Date;      
  
public class MemberDTO {  
	    /**
	     *
	     *
	    **/
        private Long memberId;  
	    /**
	     *
	     *
	    **/
        private String openId;  
	    /**
	     *
	     *姓名
	    **/
        private String name;  
	    /**
	     *
	     *手机号
	    **/
        private String phone;  
	    /**
	     *
	     *地址
	    **/
        private String address;  
	    /**
	     *
	     *是否填过个人信息
	    **/
        private String isLink;  
          
          
        public Long getMemberId(){  
            return this.memberId;  
        }  
       
        public void setMemberId(Long memberId){  
            this.memberId = memberId;
        } 
         
        public String getOpenId(){  
            return this.openId;  
        }  
       
        public void setOpenId(String openId){  
            this.openId = openId == "" ? null : openId.trim();
        } 
         
        public String getName(){  
            return this.name;  
        }  
       
        public void setName(String name){  
            this.name = name == "" ? null : name.trim();
        } 
         
        public String getPhone(){  
            return this.phone;  
        }  
       
        public void setPhone(String phone){  
            this.phone = phone == "" ? null : phone.trim();
        } 
         
        public String getAddress(){  
            return this.address;  
        }  
       
        public void setAddress(String address){  
            this.address = address == "" ? null : address.trim();
        } 
         
        public String getIsLink(){  
            return this.isLink;  
        }  
       
        public void setIsLink(String isLink){  
            this.isLink = isLink == "" ? null : isLink.trim();
        } 
         
}  