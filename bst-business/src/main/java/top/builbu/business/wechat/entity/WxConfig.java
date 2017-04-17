package top.builbu.business.wechat.entity;  

import java.util.Date;      
  
public class WxConfig {  
	    /**
	     *
	     *主键id
	    **/
        private Long id;  
	    /**
	     *
	     *微信AccessToken
	    **/
        private String accessToken;  
	    /**
	     *
	     *微信JsApiTicket
	    **/
        private String jsApiTicket;  
	    /**
	     *
	     *刷新时间
	    **/
        private Long expiresIn;  
	    /**
	     *
	     *刷新时间
	    **/
        private Long refreshTime;  
	    /**
	     *
	     *appId
	    **/
        private String corpId;  
	    /**
	     *
	     *appSecret
	    **/
        private String corpSecret;  
	    /**
	     *
	     *标示
	    **/
        private String pramToken;  
          
          
        
        public Long getId(){  
            return this.id;  
        }  
       
        public void setId(Long id){            
           
            this.id = id;
                    
        }  
          
                 
        
        public String getAccessToken(){  
            return this.accessToken;  
        }  
       
        public void setAccessToken(String accessToken){            
           
            this.accessToken = accessToken;
                    
        }  
          
                 
        
        public String getJsApiTicket(){  
            return this.jsApiTicket;  
        }  
       
        public void setJsApiTicket(String jsApiTicket){            
           
            this.jsApiTicket = jsApiTicket;
                    
        }  
          
                 
        
        public Long getExpiresIn(){  
            return this.expiresIn;  
        }  
       
        public void setExpiresIn(Long expiresIn){            
           
            this.expiresIn = expiresIn;
                    
        }  
          
                 
        
        public Long getRefreshTime(){  
            return this.refreshTime;  
        }  
       
        public void setRefreshTime(Long refreshTime){            
           
            this.refreshTime = refreshTime;
                    
        }  
          
                 
        
        public String getCorpId(){  
            return this.corpId;  
        }  
       
        public void setCorpId(String corpId){            
           
            this.corpId = corpId;
                    
        }  
          
                 
        
        public String getCorpSecret(){  
            return this.corpSecret;  
        }  
       
        public void setCorpSecret(String corpSecret){            
           
            this.corpSecret = corpSecret;
                    
        }  
          
                 
        
        public String getPramToken(){  
            return this.pramToken;  
        }  
       
        public void setPramToken(String pramToken){            
           
            this.pramToken = pramToken;
                    
        }  
          
                 
}  

