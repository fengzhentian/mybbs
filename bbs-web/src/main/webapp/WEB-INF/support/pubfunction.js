
//注意：本脚本库依赖JQuery，在使用前必须引用jquery

var numStr="0123456789";
var strStr="abcdefghijklmnopqrstuvwsyzABCDEFGHIJKLMNOPQRSTUVWSYZ";
var moneyStr = numStr + ".";
var intStr = numStr+"-";
var floatStr = moneyStr+"-";
var phoneStr = "()-#" + numStr;
var flag = 0;

/*********************************************
*字符串对象（String）扩展函数开始
*********************************************/
/*
        function:获得字符串的字节数
        return:字节数
        example:"test测试".getByte值为8
*/
String.prototype.getByte = function()
{
        var intCount = 0;
        for(var i = 0;i < this.length;i ++)
        {
            // Ascii码大于255是双字节的字符
            var ascii = this.charCodeAt(i).toString(16);
            var byteNum = ascii.length / 2.0;
            intCount += (byteNum + (ascii.length % 2) / 2);
        }
        return intCount;
}

/*
        function: 指定字符集半角字符全部转变为对应的全角字符
        parameter:
                dbcStr: 要转换的半角字符集合
        return: 转换后的字符串
*/
String.prototype.dbcToSbc = function(dbcStr)
{
        var resultStr = this;

        for(var i = 0;i < this.length;i ++)
        {
                switch(dbcStr.charAt(i))
                {
                        case ",":
                                resultStr = resultStr.replace(/\,/g,"，"); 
                                break;
                        case "!":
                                resultStr = resultStr.replace(/\!/g,"！"); 
                                break;
                        case "#":
                                resultStr = resultStr.replace(/\#/g,"＃"); 
                                break;
                        case "|":
                                resultStr = resultStr.replace(/\|/g,"|"); 
                                break;
                        case ".":
                                resultStr = resultStr.replace(/\./g,"。"); 
                                break;
                        case "?":
                                resultStr = resultStr.replace(/\?/g,"？"); 
                                break;
                        case ";":
                                resultStr = resultStr.replace(/\;/g,"；"); 
                                break;
                }
        }
        return resultStr;
}

//获取字符串按字节数指定的字串
String.prototype.bytesubstr = function(index1,index2)
{
        var resultStr = "";
        var byteCount = 0;
        for(var i = index1;i < index2;i ++)
        {
                if(i > this.length)break;
                if(this.charCodeAt(i) > 255)byteCount += 2;
                else byteCount += 1;
                if(byteCount > (index2 - index1))break;
                resultStr += this.charAt(i);
        }
        return resultStr;
}

//判断字符串是否是数字字符串，若是则返回true，否则返回false
String.prototype.isNumber = function() {
	return (this.isInt() || this.isFloat());
}
//判断字符串是否是浮点数字符串，若是则返回true，否则返回false
String.prototype.isFloat = function() {
	return /^(?:-?|\+?)\d*\.\d+$/g.test(this);
}
//判断字符串是否是整数字符串，若是则返回true，否则返回false
String.prototype.isInt = function() {
	return /^(?:-?|\+?)\d+$/g.test(this);
}
//判断字符串是否是正数字符串，若是正数则返回true，否则返回false
String.prototype.isPlus = function() {
	return this.isPlusInt() || this.isPlusFloat();
}
//判断字符串是否是正浮点数字符串，若是正数则返回true，否则返回false
String.prototype.isPlusFloat = function() {
	return /^\+?\d*\.\d+$/g.test(this);
}
//判断字符串是否是正整数字符串，若是正数则返回true，否则返回false
String.prototype.isPlusInt = function() {
	return /^\+?\d+$/g.test(this);
}
//判断字符串是否是负数字符串，若是正数则返回true，否则返回false
String.prototype.isMinus = function() {
	return this.isMinusInt() || this.isMinusFloat();
}
//判断字符串是否是负浮点数字符串，若是正数则返回true，否则返回false
String.prototype.isMinusFloat = function() {
	return /^-\d*\.\d+$/g.test(this);
}
//判断字符串是否是负整数字符串，若是正数则返回true，否则返回false
String.prototype.isMinusInt = function() {
	return /^-\d+$/g.test(this);
}

//判断字符串是否只包含单词字符，若是则返回true，否则返回false
String.prototype.isLeastCharSet = function() {
	return !(/[^A-Za-z0-9_]/g.test(this));
}
//判断是否包含字母和数字
String.prototype.isCharAndNum = function(){
    return !(/[\W]/g.test(this));
}
//判断是否为合法的域帐号
String.prototype.isDomainUser = function() {
    return (this.isCharAndNum() || this.vaildUser()|| this.isEmail());
}
String.prototype.vaildUser = function(){
     return /^[\w-]+(\.[\w-]+)+$/g.test(this);
}
//判断字符串是否是Email字符串，若是则返回true，否则返回false
String.prototype.isEmail = function() {
    return /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/g.test(this);
}
//判断字符串是否是邮政编码字符串，若是则返回true，否则返回false
String.prototype.isZip = function() {
	return /^\d{6}$/g.test(this);
}
//判断字符串是否是固定电话号码字符串，若是则返回true，否则返回false
String.prototype.isFixedTelephone = function() {
	return /^(\d{2,4}-)?((\(\d{3,5}\))|(\d{3,5}-))?\d{3,18}(-\d+)?$/g.test(this);
}
//判断字符串是否是手机电话号码字符串，若是则返回true，否则返回false
String.prototype.isMobileTelephone = function() {
	return /^13\d{9}$/g.test(this);
}
//判断字符串是否是电话号码字符串，若是则返回true，否则返回false
String.prototype.isTelephone = function() {
	return this.isMobileTelephone() || this.isFixedTelephone();
}
//判断字符串是否是日期字符串，若是则返回true，否则返回false
String.prototype.isDate = function() {
	return /^\d{1,4}-(?:(?:(?:0?[1,3,5,7,8]|1[0,2])-(?:0?[1-9]|(?:1|2)[0-9]|3[0-1]))|(?:(?:0?[4,6,9]|11)-(?:0?[1-9]|(?:1|2)[0-9]|30))|(?:(?:0?2)-(?:0?[1-9]|(?:1|2)[0-9])))$/g.test(this);
}
//判断字符串是否是时间字符串，若是则返回true，否则返回false
String.prototype.isTime = function() {
	return /^(?:(?:0?|1)[0-9]|2[0-3]):(?:(?:0?|[1-5])[0-9]):(?:(?:0?|[1-5])[0-9]).(?:\d{1,3})$/g.test(this);
}
//判断字符串是否是日期时间字符串，若是则返回true，否则返回false
String.prototype.isDateTime = function() {
	return /^\d{1,4}-(?:(?:(?:0?[1,3,5,7,8]|1[0,2])-(?:0?[1-9]|(?:1|2)[0-9]|3[0-1]))|(?:(?:0?[4,6,9]|11)-(?:0?[1-9]|(?:1|2)[0-9]|30))|(?:(?:0?2)-(?:0?[1-9]|(?:1|2)[0-9]))) +(?:(?:0?|1)[0-9]|2[0-3]):(?:(?:0?|[1-5])[0-9]):(?:(?:0?|[1-5])[0-9])$/g.test(this);
}
//比较日期字符串，若相等则返回0，否则返回当前日期字符串和目标字符串之间相差的毫秒数，若其中一个字符串不符合日期或日期时间格式，则返回null
String.prototype.compareDate = function(target) {
	var thisDate = this.toDate();
	var targetDate = target.toDate();
	if (thisDate == null || targetDate == null) {
		return null;
	}
	else {
		return thisDate.getTime() - targetDate.getTime();
	}
}
//判断日期字符串指定的时期是否是当前日期，若是则返回true，否则返回false
String.prototype.isToday = function() {
	return this.trim().split(' ')[0].compareDate(getSysDate()) == 0;
}
//判断日期字符串指定的时期是否是当前日期之前，若是则返回true，否则返回false
String.prototype.isBeforeDate = function(baseDate) {
	if (baseDate == null) {
		baseDate = getSysDate();
	}
	return this.trim().split(' ')[0].compareDate(baseDate.trim().split(' ')[0]) < 0;
}
//判断日期字符串指定的时期是否是当前日期之后，若是则返回true，否则返回false
String.prototype.isAfterDate = function(baseDate) {
	if (baseDate == null) {
		baseDate = getSysDate();
	}
	return this.trim().split(' ')[0].compareDate(baseDate.trim().split(' ')[0]) > 0;
}

//判断日期时间字符串指定的时期是否是指定日期时间之前，若是则返回true，否则返回false
String.prototype.isBeforeDateTime = function(baseDateTime) {
	if (baseDateTime == null) {
		baseDateTime = getSysDateTime();
	}
	return this.trim().compareDate(baseDateTime.trim()) < 0;
}
//判断日期时间字符串指定的时期是否是指定日期时间之后，若是则返回true，否则返回false
String.prototype.isAfterDateTime = function(baseDateTime) {
	if (baseDateTime == null) {
		baseDateTime = getSysDateTime();
	}
	return this.trim().compareDate(baseDateTime.trim()) > 0;
}



//判断字符串中是否含有特殊字符，若有则返回true，否则返回false
String.prototype.hasSpecialChar = function() {
	specialChars.test('');
	return specialChars.test(this);
}
//删除字符串中的空格
String.prototype.deleteSpace = function() {
	return this.replace(/( +)|(　+)/g, '');
}
//删除字符串中指定的字符串
String.prototype.remove = function(str) {
	if (str == null || str == '') {
		return this;
	}
	return this.replace(str.toRegExp('g'), '');
}
//将字符串中包含的find字符串替换成target字符串，返回替换后的结果字符串
String.prototype.replaceByString = function(find, target) {
	return this.replace(find.toRegExp('g'), target);
}
//将字符串转换成相应的正则表达式
String.prototype.toRegExp = function(regType) {
	if (regType == null || regType.trim() == '') {
		regType = 'g';
	}
	var find = ['\\\\', '\\$', '\\(', '\\)', '\\*', '\\+', '\\.', '\\[', '\\]', '\\?', '\\^', '\\{', '\\}', '\\|', '\\/'];
	var str = this;
	for (var i = 0; i < find.length; i++) {
		str = str.replace(new RegExp(find[i], 'g'), find[i]);
	}
	return new RegExp(str, regType);
}
//将字符串转换成Date对象，要求字符串必须符合日期或日期时间格式，否则返回null
String.prototype.toDate = function() {
	if (this.isDate()) {
		var data = this.split('-');
		return new Date(parseInt(data[0].replace(/^0+/g, '')), parseInt(data[1].replace(/^0+/g, '')) - 1, parseInt(data[2].replace(/^0+/g, '')));
	}
	else if (this.isDateTime()) {
		var data = this.split(' ');
		var date = data[0].split('-');
		var time = data[1].split(".")[0].split(':');
		return new Date(parseInt(date[0].replace(/^0+/g, '')), parseInt(date[1].replace(/^0+/g, '')) - 1, parseInt(date[2].replace(/^0+/g, '')), 
			parseInt(time[0].replace(/^0+/g, '')), parseInt(time[1].replace(/^0+/g, '')), parseInt(time[2].replace(/^0+/g, '')));
	}
	else {
		return null;
	}
}
//将字符串按HTML需要的编码方式编码
String.prototype.encodeHtml = function() {
	var strToCode = this.replace(/</g,"&lt;");
	strToCode = strToCode.replace(/>/g,"&gt;");
	return strToCode;
}
//将字符串按HTML需要的编码方式解码
String.prototype.decodeHtml = function() {
	var strToCode = this.replace(/&lt;/g,"<");
	strToCode = strToCode.replace(/&gt;/g,">");
	return strToCode;
}
/*********************************************
*字符串对象（String）扩展函数结束
*********************************************/
function saveCheck(form){
	var obj = null;
    var t = null;
	var i;
    for (i=0;i<form.length;i++)
    {    
		 obj = form.elements[i];		 	 		 
		 if(packUp(obj)){
		     if ($(obj).attr('v_type') == "string") forSaveString(obj);
		     else if ($(obj).attr('v_type') == "zip") forZip(obj); 
	         else if($(obj).attr('v_type') == "money") forMoney(obj);
	         else if($(obj).attr('v_type') == "0_9") for0_9(obj);
			 else if($(obj).attr('v_type') == "int") forInt(obj);
			 else if($(obj).attr('v_type') == "float") forFloat(obj);
			 else if($(obj).attr('v_type') == "email") forEmail(obj);
			 else if($(obj).attr('v_type') == "date") forDate(obj);
			 else if($(obj).attr('v_type') == "pwd") forPwd(obj);
			 else if($(obj).attr('v_type') == "phone") forPhone(obj);
			 else if($(obj).attr('v_type') == "idcard") forIdCard(obj);
			 else if($(obj).attr('v_type') == "char") forChar(obj);
			 else if($(obj).attr('v_type') == "chinese") forChinese(obj);
         }  
                
		if(flag == 1) {
	      flag = 0;
		  return false;
		}

     }
	 
     return true;
}



function check(form){
	var obj = null;
    var t = null;
	var i;
    for (i=0;i<form.length;i++)
    {    
		 obj = form.elements[i]; 		 
		 if(packUp(obj)){
		     if($(obj).attr('v_type') == "string") forString(obj);	     
	         else if($(obj).attr('v_type') == "zip") forZip(obj);
	         else if($(obj).attr('v_type') == "money") forMoney(obj);
	         else if($(obj).attr('v_type') == "0_9") for0_9(obj);
			 else if($(obj).attr('v_type') == "int") forInt(obj);
			 else if($(obj).attr('v_type') == "float") forFloat(obj);
			 else if($(obj).attr('v_type') == "email") forEmail(obj);
			 else if($(obj).attr('v_type') == "date") forDate(obj);
			 else if($(obj).attr('v_type') == "pwd") forPwd(obj);
			 else if($(obj).attr('v_type') == "phone") forPhone(obj);
			 else if($(obj).attr('v_type') == "idcard") forIdCard(obj);
			 else if($(obj).attr('v_type') == "char") forChar(obj);
			 else if($(obj).attr('v_type') == "chinese") forChinese(obj);
         }  
                
		if(flag == 1) {
	      flag = 0;
		  return false;
		}

     }
	 
     return true;
}

function  packUp(obj)
{   
	if (obj == null){
	   alert("非法对象");
   		return false;
 	}
	if (obj.tagName && obj.tagName.toLowerCase() == 'object') {
	    return false;
	}
	if ($(obj).attr('v_must') == null || $(obj).attr('v_must') == ""){
	   return false;
	}	
	 
	 if(obj.style.display=='none'){	
        
           return false;
        }	 
	if (obj.value != null){
		obj.value = trimSubStr(trimSubStr(obj.value," ",1)," ",2);
	}
	 
	 
	//如果属性为空，则创建默认值

    if ($(obj).attr('v_name') == null) {
	    $(obj).attr('v_name', new String(obj.name));
	}

	if ($(obj).attr('v_type') == null) {
   	    $(obj).attr('v_type', new String("string"));  
   	}

   	if ($(obj).attr('v_minlength') == null) {
	    $(obj).attr('v_minlength', new String("0"));
    }

	if ($(obj).attr('v_maxlength') == null) {
   	    $(obj).attr('v_maxlength', obj.getAttribute("maxlength"));
    }
 
   	if ($(obj).attr('v_maxvalue') == null){	 
	 	$(obj).attr('v_maxvalue',new String(""));

    }
    if ($(obj).attr('v_minvalue') == null) {	 
	 	$(obj).attr('v_minvalue',new String(""));
    }
    		 
	return true;	
}




function isMadeOf(val,str)
{

	var jj;
	var chr;
	for (jj=0;jj<val.length;++jj){
		chr=val.charAt(jj);
		if (str.indexOf(chr,0)==-1)
			return false;			
	}
	
	return true;
}


function isNotNull(val)
{
	if (val =="")
		return (false);
	if (val == null)
		return (false);
	
		return (true);
}

function isSizeOf(val,min,max)
{
    var maxval = parseFloat(max);
	var minval = parseFloat(min);
	var selval = parseFloat(val);
	
	if (isNaN(selval)){
	  return false;
	}
	
	if (!isNaN(maxval)){
	  if (selval > maxval){
	    return false;
	  }
	}
	if (!isNaN(minval)){
	  if (selval < minval){
	    return false;
	  }
	}
	
    /*
	if (val < min || val > max){
	  return false;	
	}
	*/
		
	return (true);
}


function isLengthOf(val,min,max)
{   
    	
	var minlen = parseInt(min);
	var maxlen = parseInt(max);

	if (!isNaN(maxlen)){
	  if (val.length > maxlen){
	 // if (val.getByte() > maxlen){
	    return false;
	  }
	}
	if (!isNaN(minlen)){
	  if (val.length < minlen){
	  //if (val.getByte() < minlen){
	    return false;
	  }
	}
	
	/*
	if (val.length < minlen || val.lengh > maxlen ){
	  return false;
	}
	*/
	return true;
}


function isValid_dot(val)
{   
    
    var subvalue;
    
    if (val.indexOf(".",0) != -1){
	
	   subvalue = val.substring(val.indexOf(".",0)+1);
	   
	  if (subvalue.indexOf(".",0) != -1){
	    return false;  
	  }
	  
	}
    
	return true;
	
}


function isValid_negative(val)
{
    
    var subvalue;
    
    if (val.indexOf("-",0) != -1){
	
	   if (val.indexOf("-",0) > 0){
	     return false;
	   }
	   
	   subvalue = val.substring(val.indexOf("-",0)+1);
	  
	  if (subvalue.indexOf("-",0) != -1){
	    return false;  		
	  }
	  
	}
    
	return true;
	

}


function isRight_length(val,num)
{
     var len = parseInt(num);
	 
	 if (isNaN(len)){ return true;}
	 
	 if (val.length != len){
	   return false;
	 }

     return true;

}


  function trimSubStr(ATrimStr,ASubStr,AWhere){  
    var tTrLength,tSbLength,tempLength;
    var tempString;
    var i; 

    tTrLength = ATrimStr.length;
    tSbLength = ASubStr.length;

    if (tSbLength == 0){return ATrimStr;}
    if (tSbLength > tTrLength){return ATrimStr;}
  
    tempString = ATrimStr;
    switch(AWhere){
      case 0://所有
        do{
          tempLength = tempString.length;
          tempString = tempString.replace(ASubStr,"");
        } while(tempLength != tempString.length);
        break; 
      case 1://左
        while (true){
          if (tempString.length < tSbLength) break;
          for (i = 0;i < tSbLength;i++)
            if (ASubStr.charAt(i) != tempString.charAt(i)) 
              return tempString;
          tempString = tempString.replace(ASubStr,"");  
        };  
      case 2://右
        while(true){
          tempLength = tempString.length;
          if (tempLength < tSbLength){return tempString;}
          for (i = 0;i < tSbLength;i ++){
            if (ASubStr.charAt(i) != tempString.charAt(tempLength - tSbLength+i)){
              return tempString;
            }  
          }    
          tempString = tempString.substr(0,tempLength-tSbLength); 
        };
      default:
        return tempString;
    }
    return tempString; 
  }


function forDate(useryear,usermonth,userday)
{
	var myyear;
	var mymonth;
	var myday;
	//myyear=parseInt(useryear);
	//mymonth=parseInt(usermonth);
	//myday=parseInt(userday);
	myyear=useryear;
	mymonth=usermonth;
	myday=userday;
	if (myyear < 1950 || myyear > 2050 ||mymonth < 1 ||mymonth > 12 || myday < 1 || myday > 31)
		return (false);
        if(mymonth==4 || mymonth==6 || mymonth==9 || mymonth==11)
        { 
        	if(myday>30)
            		return (false);
        }
	if(myyear%4==0)
	{
	 	if((myyear%100==0 && myyear%400==0) || myyear%100!=0)
          	{
            		if(mymonth==2 && myday>29)
             			return (false);
            		else
	     			return (true);
          	}
        }
	else
	{
          	if(mymonth==2 && myday>28)
             		return (false);
             	else
             		return (true);
	}
}



function  forString(obj)
{     
      
	  if ($(obj).attr('v_must')!="0"){
	    
	    if (obj.value.length == 0){
		   alert("'" + $(obj).attr('v_name') + "'为必填项，请务必填写");
		   flag = 1;
		  
		   return false;
		}
	    
	  }

	  if (!isLengthOf(obj.value,$(obj).attr('v_minlength'),$(obj).attr('v_maxlength'))){
	    flag = 1;
		alert("'"+$(obj).attr('v_name')+"'的长度不正确！最大长度为" + $(obj).attr('v_maxlength') + ",注意一个汉字的长度为2！");
		obj.focus();
		return false;
	  
	  }
  
	  return true;
      
}

function  forSaveString(obj)
{     
      

	  if (!isLengthOf(obj.value,$(obj).attr('v_minlength'),$(obj).attr('v_maxlength'))){
	    flag = 1;
		alert("'"+$(obj).attr('v_name')+"'的长度不正确！最大长度为" + $(obj).attr('v_maxlength') + ",注意一个汉字的长度为2！");
		obj.focus();
		return false;
	  
	  }
  
	  return true;
      
}

function forZip(obj)
{
    if (!forString(obj)){
	  flag = 1;
	 
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}
  
    if (!isMadeOf(obj.value,numStr)){
        flag = 1;
        alert("'" + $(obj).attr('v_name') + "'的值不正确！请输入数字！");
	   
	    return false;
      }
	      
    if (!isRight_length(obj.value,"6")){
      flag = 1;
      alert("'"+$(obj).attr('v_name')+"'的值不正确！长度有错误！(6位数字)");
	 
	  return false;
    } 

    return true;
}

function forMoney(obj)
{   
    if (!forString(obj)){
	  flag = 1;
	 
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}
		
    if (!isMadeOf(obj.value,moneyStr)){
      flag = 1;
      alert("'" + $(obj).attr('v_name') + "'的值不正确！请输入数字！");
	 
	  return false;
    }
     
	if (!isValid_dot(obj.value)){
	    flag = 1;
	    alert("'" + $(obj).attr('v_name') + "'的值不正确！小数点有错误！");
	   
	    return false;  
	}
	
	var temp = obj.value;
	if (temp.indexOf(".") != -1) {
		var str = temp.substring(temp.indexOf(".")+1, temp.length);
		if(str.length > 2){
			flag = 1;
			alert("'" + $(obj).attr('v_name') + "'的值不正确！小数点有错误！");
			obj.focus();
			return false;
		}
	}
	
	if (!isSizeOf(obj.value,$(obj).attr('v_minvalue'),$(obj).attr('v_maxvalue'))){
	  flag = 1;
	  alert("'" + $(obj).attr('v_name') + "'的值不正确！超出规定范围！");
	 
	  return false;
	} 
	
	return true;
	
}


function for0_9(obj)
{    
    if (!forString(obj)){
	  flag = 1;
	 
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}
    
	if (!isMadeOf(obj.value,numStr)){
      flag = 1;
      alert("'" + $(obj).attr('v_name') + "'的值不正确！请输入数字！");
	 
	  return false;
    }
	
	return true;
	
}

/*
**校验字符串是否为英文字母
*增加人：李启平
*Date  ：2004-10-27
*/
function forChar(obj)
{    
    if (!forString(obj)){
	  flag = 1;
	 
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}
    
	if (!isMadeOf(obj.value,strStr)){
      flag = 1;
      alert("'" + $(obj).attr('v_name') + "'的值不正确！请输入字母！");
	 
	  return false;
    }
	
	return true;
	
}

function forInt(obj)
{  
    if (!forString(obj)){
	  flag = 1;
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}
   
    if (!isMadeOf(obj.value,intStr)){
      flag = 1;
      alert("'" + $(obj).attr('v_name') + "'的值不正确！请输入数字！");

	  return false;
    }
	
	if (!isValid_negative(obj.value)){
      flag = 1;
      alert("'" + $(obj).attr('v_name') + "'的值不正确！'-'符号有错误！");
	 
	  return false;
    }
	
	if (!isSizeOf(obj.value,$(obj).attr('v_minvalue'),$(obj).attr('v_maxvalue'))){
	  flag = 1;
	  alert("'" + $(obj).attr('v_name') + "'的值不正确！超出规定范围！");
	 
	  return false;
	}
    
   return true;
   
}


function forFloat(obj)
{
    if (!forString(obj)){
	  flag = 1;
	 
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}
   
    if (!isMadeOf(obj.value,floatStr)){
      flag = 1;
      alert("'" + $(obj).attr('v_name') + "'的值不正确！请输入数字！");
	 
	  return false;
    }
	
	if (!isValid_dot(obj.value)){
	    flag = 1;
	    alert("'" + $(obj).attr('v_name') + "'的值不正确！小数点有错误！");
	   
	    return false;  
	}	
	
	if (!isValid_negative(obj.value)){
      flag = 1;
      alert("'" + $(obj).attr('v_name') + "'的值不正确！'-'符号有错误！");
	 
	  return false;
    }
	
	if (!isSizeOf(obj.value,$(obj).attr('v_minvalue'),$(obj).attr('v_maxvalue'))){
	  flag = 1;
	  alert("'" + $(obj).attr('v_name') + "'的值不正确！超出规定范围！");
	 
	  return false;
	}

   return true;

}

function forPhone(obj)
{
    if (!forString(obj)){
	  flag = 1;
	 
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}
	
	if (!isMadeOf(obj.value,phoneStr)){
	  flag = 1;
	  alert("'" + $(obj).attr('v_name') + "'的值不正确！请填写数字,可以包含(,),－,#符号"); 
	 
	  return false; 
	}

}

function forEmail(obj)
{
    if (!forString(obj)){
	  flag = 1;
	 
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}
	
    //var myReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/; 
    if(obj.value.isEmail()){
	  return true; 
	}
	alert("'" + $(obj).attr('v_name') + "'的值不正确！请确信填写正确的电子邮箱！");
	flag = 1;
	obj.focus();
    return false; 

}

function forIdCard(obj)
{
    if (!forString(obj)){
	  flag = 1;
	 
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	} 
	
	/*
	if (!isMadeOf(obj.value,numStr)){
	  flag = 1;
	  alert("'" + $(obj).attr('v_name') + "'的值不正确！身份证必须填写数字！");
	 
	  return false;
	}
	*/
	
	if (!isRight_length(obj.value,"15") && !isRight_length(obj.value,"18")){
	  flag = 1;
	  alert("'" + $(obj).attr('v_name') + "'的值不正确！请输入正确的身份证号！"); //身份证长度不正确(只能为15或18位)！");
	 
	  return false;
	}
	
	return true;

}

function forDate(obj)
{
	var myyear;
	var mymonth;
	var myday;
    if (!forString(obj)){
	  flag = 1;
	 
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}
  	
  	var Date = obj.value;
  	if(Date != null && Date != ""){
  		var msg = "日期格式不合法，请使用类似“2004-01-01 00:00:00”或“2004-01-01”的格式！";
  		if(!Date.isDate()&&!Date.isDateTime()){
  			//if(Date.getByte() != 8){
  			//	flag = 1;
  			//	alert("'" + $(obj).attr('v_name') + "'的值不正确！" + msg);
		    	//obj.focus();
		   // 	return false;
  			//}
  			//myyear = Date.substring(0, 4);
			//mymonth = Date.substring(4, 6);
			//myday = Date.substring(6, 8);
  			//Date = myyear + "-" + mymonth + "-" + myday;
  			//if(!Date.isDate()){
  				flag = 1;
  				alert("'" + $(obj).attr('v_name') + "'的值不正确！" + msg);
		    	//obj.focus();
		    	return false;
		   // }
		}else{
			myyear = Date.substring(0, 4);
			mymonth = Date.substring(5, 7);
			myday = Date.substring(8, 10);
		}
	}
	if (!isMadeOf(myyear,numStr)){
		flag = 1;
		alert("'" + $(obj).attr('v_name') + "'的值不正确！" + msg);
    	obj.focus();
	    return false;
    }
    if (!isMadeOf(mymonth,numStr)){
    	flag = 1;
    	alert("'" + $(obj).attr('v_name') + "'的值不正确！" + msg);
    	obj.focus();
	    return false;
    }
    if (!isMadeOf(myday,numStr)){
    	flag = 1;
    	alert("'" + $(obj).attr('v_name') + "'的值不正确！" + msg);
    	obj.focus();
	    return false;
    }
    return true;
}

/********************************** chinese ***************************************/
/**
*校验字符串是否为中文
*返回值：
*如果为空，定义校验通过，           返回true
*如果字串为中文，校验通过，         返回true
*如果字串为非中文，                返回false    参考提示信息：必须为中文！
*增加人：李启平
*Date  ：2004-10-27
*/
function forChinese(obj)
{
	
	if (!checkIsChinese(obj.value)){
	  flag = 1;
	  alert("'" + $(obj).attr('v_name') + "'的值不正确！请填写中文"); 
	 
	  return false; 
	}

}
function checkIsChinese(str)
{
    //如果值为空，通过校验
    if (str == "")
        return true;
    var pattern = /^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/gi;
    if (pattern.test(str))
        return true;
    else
    {
        return false;
    }
}

function getNumber(value)
{ if(value!=null&&value!="undefined") {
  if(value=="" ||!isMadeOf(value,floatStr))
   return 0;
  else
   return parseInt(value);
} else 
  return 0;
}
function getStr(value)
{ 
	if(value!=null&&value!="undefined") 
		return value;
	else
		return "";
}


//验证输入的信息是否是数字
function isInt(obj) {
    if (obj.toString().length == 0) {
        return false;
    }
    var pattern = /^-?\d+$/;
    return pattern.test(obj);
}
//验证输入的信息是否是字母
function isString(obj) {
    if (obj.toString().length == 0) {
        return false;
    }
    var pattern = /^[a-zA-Z]+$/;
    return pattern.test(obj);
}

//替换数据
function replaceSubstring(inputString, fromString, toString) {
  var temp = inputString;
  if (fromString == "") {
      return inputString;
  }
  if (toString.indexOf(fromString) == -1) {
      while (temp.indexOf(fromString) != -1) {
          var toTheLeft = temp.substring(0, temp.indexOf(fromString));
          var toTheRight = temp.substring(temp.indexOf(fromString) + fromString.length, temp.length);
          temp = toTheLeft + toString + toTheRight;
      }
  } else {
      var midStrings = new Array("~", "`", "_", "^", "#");
      var midStringLen = 1;
      var midString = "";
      while (midString == "") {
          for (var i = 0; i < midStrings.length; i++) {
              var tempMidString = "";
              for (var j = 0; j < midStringLen; j++) { tempMidString += midStrings; }
              if (fromString.indexOf(tempMidString) == -1) {
                  midString = tempMidString;
                  i = midStrings.length + 1;
              }
          }
      }
      while (temp.indexOf(fromString) != -1) {
          var toTheLeft = temp.substring(0, temp.indexOf(fromString));
          var toTheRight = temp.substring(temp.indexOf(fromString) + fromString.length, temp.length);
          temp = toTheLeft + midString + toTheRight;
      }
      while (temp.indexOf(midString) != -1) {
          var toTheLeft = temp.substring(0, temp.indexOf(midString));
          var toTheRight = temp.substring(temp.indexOf(midString) + midString.length, temp.length);
          temp = toTheLeft + toString + toTheRight;
      }
  }
  return temp;
}