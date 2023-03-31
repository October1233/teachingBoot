/**
 * 教育分期公共js
 */


function formatInfoMask(value,row,index){
	if(value ==null || value == 'VIPKID0000' || value == 'JY000'){
		return "";
	}
	return doMaskInfo(value);
}
function doMaskInfo(into){
	if(!into){
		return "";
	}
	var lenth = into.length;
	if(lenth<8){
		return into;
	}
	var mask = "";
	for(var i=0;i<lenth-7;i++){
		mask = mask + "*";
	}
	return into.substring(0,3)+mask+into.substring(lenth-4);
}

function formatNameMask(value,row,index){
	if(value==null || value=='VIPKID' || value=='JY'){
		return '';
	}
	return doMaskNameInfo(value);
}
function doMaskNameInfo(info){
	var lenth = info.length;
	if(lenth<2){
		return info;
	}
	var mask = "";
	for(var i=0;i<lenth-1;i++){
		mask = mask + "*";
	}
	return info.substring(0,1)+mask;
}

function formatAmt(value,row,index){
	 if(value!=null && value!='')
      return value.toFixed(2);
}

function formatPlanStatus(value,row,index){
	 if(value == 'N'){
      return '未结清';
	  }else if(value == 'Y'){
		return '已结清';
	  }
}
function showRemark(value,row,index){
	 if(row!=null){
      if(row.order_sts != null && row.order_sts != ''){
   	   if(row.order_sts == '02' || row.order_sts == '57'){
             return value;
   	   }else{
             return '通过';
              }
	 }else{
		 return '';
	 }
  }
}
function formatMerNo(value,row,index){
	 if(value != null && value == 'msxd0001'){
		 return '民商惠小贷';
	  }
}
function showIsRefund(value,row,index){
	 if(value == 'N'){
     return '否';
	  }else if(value == 'Y'){
		return '是';
	  }
}

function showRefundStatus(value,row,index){
	if(value==1){
		return "退费成功";
	}else if(value==2){
		return "退费失败";
	}else if(value==0){
		return "未处理";
	}else if(value==3){
		return "托管退费处理中";
	}else if(value==4){
		return "托管退费成功，本地账务修改失败或未知";
	}
}


