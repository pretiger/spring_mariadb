function getXMLHttpRequest(){
	var xhr;
	if(window.ActiveXObject){
		try{
			xhr=new ActiveXObject("Msxml2.HTTPXML");
			try{
				xhr=new ActiveXObject("Microsoft.HTTPXML");
			}catch(e){
				xhr=null;
			}
		}catch(e1){
			xhr=null;
		}
	}else if(window.XMLHttpRequest){
		try{
			xhr=new XMLHttpRequest();
		}catch(e){
			xhr=null;
		}
	}
	return xhr;
}