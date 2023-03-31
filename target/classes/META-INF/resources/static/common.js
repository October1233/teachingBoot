/**
 * easyui-格式化函数集
 */
var fmts = {
		aduitStatus:function(value,row,index){
			if(value==0){
				return "待审核";
			}else if(value==1){
				return "审核中";
			}else if(value==2){
				return "审核拒绝";
			}else if(value==3){
				return "审核通过";
			}
		},
		dotKeep:function(value,row,index){
			if(value!=null){
		        return value.toFixed(2);
			}
		},
		formatProduct:function(val,row,index){
			if(val==1){
				return "学易贷";
			}else if(val==2){
				return "卡易还";
			}
		},
		formatRepaymentMode:function(val,row,index){
			if(val==1||val=='01'){
				return "等额本息";
			}else if(val==2||val=='02'){
				return "等额本金";
			}else if(val==3||val=='03'){
				return "一次还本付息";
			}else if(val==4||val=='04'){
				return "等本等息";
			}else if(val==5||val=='05'){
				return "按月付息到期一次还本";
			}else if(val==99||val=='99'){
				return "其他";
			}
		},
		formatDebitDayType:function(val,row,index){
		    if(val==1||val=='01'){
				return "放款日为扣款日";
			}else if(val==2||val=='02'){
				return "固定扣款日";
			}
		},
		formatDebitDayCategory:function(val,row,index){
		    if(val==1||val=='01'){
				return "对日";
			}else if(val==2||val=='02'){
				return "对月";
			}else if(val==3||val=='03'){
				return "对季";
			}else if(val==4||val=='04'){
				return "对年";
			}else if(val==99||val=='99'){
				return "其他";
			}
		},
		formatPaymentMethod:function(val,row,index){
			if(val==1||val=='01'){
				return "期缴";
			}else if(val==2||val=='02'){
				return " 趸缴";
			}else if(val==99||val=='99'){
				return "其他";
			}
		},
		formatLoanType:function(val,row,index){
		    if(val==1||val=='01'){
				return "房贷";
			}else if(val==2||val=='02'){
				return "车贷";
			}else if(val==3||val=='03'){
				return "消费贷";
			}else if(val==4||val=='04'){
				return "经营贷";
			}else if(val==99||val=='99'){
				return "其他";
			}
		},
		formatRateType:function(val,row,index){
		    if(val==10||val=='10'){
				return "年利率";
			}else if(val==20||val=='20'){
				return "月利率";
			}else if(val==30||val=='30'){
				return "日利率";
			}
		},
		formaLoanStatus:function (val,row,index){
			if(val==110){
				return "标的录入";
			}else if(val==180){
				return "等待审核";
			}else if(val==220){
				return "审核打回";
			}else if(val==240){
				return "通过审核";
			}else if(val==260){
				return "复审打回";
			}else if(val==270){
				return "营销设置";
			}else if(val==275){
				return "打回营销";
			}else if(val==280){
				return "通过复审";
			}else if(val==300){
				return "开标中";
			}else if(val==400){
				return "满标";
			}else if(val==500){
				return "还款中";
			}else if(val==550){
				return "逾期";
			}else if(val==560){
				return "提前还款";
			}else if(val==570){
				return "还款异常";
			}else if(val==600){
				return "已还清";
			}else if(val==700){
				return "流标";
			}else if(val==750){
				return "结标";
			}else if(val==800){
				return "已放款未确认";
			}
		},
		formatYYYYMMDD:function(value,row,index){
			if(value){
				return value.split(" ")[0];
			}
		},
		repayType:function(value,row,index){
			if(value==1){
				return "新增";
			}else if(value==2){
				return "更新";
			}
		},
		payMode:function(value,row,index){
			if(value=="01"){
				return "银行扣款";
			}else if(value=="02"){
				return "现金还款";
			}else if(value=="03"){
				return "第三方扣款";
			}else if(value=="99"){
				return "其他";
			}
		},
		payType:function(value,row,index){
			if(value=="01"){
				return "正常扣款";
			}else if(value=="02"){
				return "逾期扣款";
			}else if(value=="03"){
				return "提前还款扣款";
			}else if(value=="04"){
				return "理赔扣款";
			}else if(value=="05"){
				return "其他暂收款(溢缴款)";
			}else if(value=="08"){
				return "暂收款";
			}else if(value=="10"){
				return "代偿_合作机构";
			}else if(value=="12"){
				return "代偿后还款_借款人";
			}else if(value=="1"){
				return "合作机构买断";
			}else if(value=="2"){
				return "买断后还款记录";
			}else if(value=="3"){
				return "未到账";
			}else if(value=="4"){
				return "回购";
			}
		},
		transType:function(value,row,index){
			if(value==1){
				return "充值";
			}else if(value==2){
				return "提现";
			}else if(value==3){
				return "收利息";
			}else{
                return value;
            }
		},
		transDir:function(value,row,index){
			if(value==1){
				return "收入";
			}else if(value==2){
				return "支出";
			}
		},
		formatArrears:function(val,row,index){
			var plannedAmount = row.plannedAmount;
			var repaidAmount = row.repaidAmount;
			var overamt = plannedAmount-repaidAmount;
			if(overamt!=null){
				return overamt.toFixed(2);
			}
		},
		dotAccKeep:function (val,row,index){
			if(row.status!=3){
				return "--";
			}
			if(val!=null){
		        return val.toFixed(2);
			}
		}
		
}