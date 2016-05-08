function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
// 本地时钟
function clockon() {
	var now = new Date();
	var year = now.getFullYear(); // getFullYear getYear
	var month = now.getMonth();
	var date = now.getDate();
	var day = now.getDay();
	var hour = now.getHours();
	var minu = now.getMinutes();
	var sec = now.getSeconds();
	var week;
	month = month + 1;
	if (month < 10)
		month = "0" + month;
	if (date < 10)
		date = "0" + date;
	if (hour < 10)
		hour = "0" + hour;
	if (minu < 10)
		minu = "0" + minu;
	if (sec < 10)
		sec = "0" + sec;
	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	week = arr_week[day];
	var time = "";
	time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu + ":" + sec + " " + week;

	$("#bgclock").html(time);

	var timer = setTimeout("clockon()", 200);
}
function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [ , "" ])[1].replace(/\+/g, '%20')) || null;
}

function formatTime(millisecond)
{
	var et = '';
  if (millisecond > 0) {
      var time = new Date();
      time.setTime(millisecond);
      et = time.format('yyyy-MM-dd HH:mm:ss');
  } else {
      et = '';
  }
  return et;
}