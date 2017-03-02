/*
@license
dhtmlxScheduler v.4.4.0 Stardard

This software is covered by GPL license. You also can obtain Commercial or Enterprise license to use it in non-GPL project - please contact sales@dhtmlx.com. Usage without proper license is prohibited.

(c) Dinamenta, UAB.
*/
scheduler.templates.calendar_month=scheduler.date.date_to_str("%F %Y"),scheduler.templates.calendar_scale_date=scheduler.date.date_to_str("%D"),scheduler.templates.calendar_date=scheduler.date.date_to_str("%d"),scheduler.config.minicalendar={mark_events:!0},scheduler._synced_minicalendars=[],scheduler.renderCalendar=function(e,t,a){var r=null,i=e.date||scheduler._currentDate();if("string"==typeof i&&(i=this.templates.api_date(i)),t)r=this._render_calendar(t.parentNode,i,e,t),scheduler.unmarkCalendar(r);else{
var n=e.container,s=e.position;if("string"==typeof n&&(n=document.getElementById(n)),"string"==typeof s&&(s=document.getElementById(s)),s&&"undefined"==typeof s.left){var d=getOffset(s);s={top:d.top+s.offsetHeight,left:d.left}}n||(n=scheduler._get_def_cont(s)),r=this._render_calendar(n,i,e),r.onclick=function(e){e=e||event;var t=e.target||e.srcElement;if(-1!=t.className.indexOf("dhx_month_head")){var a=t.parentNode.className;if(-1==a.indexOf("dhx_after")&&-1==a.indexOf("dhx_before")){var r=scheduler.templates.xml_date(this.getAttribute("date"));
r.setDate(parseInt(t.innerHTML,10)),scheduler.unmarkCalendar(this),scheduler.markCalendar(this,r,"dhx_calendar_click"),this._last_date=r,this.conf.handler&&this.conf.handler.call(scheduler,r,this)}}}}if(scheduler.config.minicalendar.mark_events)for(var l=scheduler.date.month_start(i),o=scheduler.date.add(l,1,"month"),_=this.getEvents(l,o),c=this["filter_"+this._mode],h={},u=0;u<_.length;u++){var v=_[u];if(!c||c(v.id,v)){var f=v.start_date;for(f.valueOf()<l.valueOf()&&(f=l),f=scheduler.date.date_part(new Date(f.valueOf()));f<v.end_date&&(h[+f]||(h[+f]=!0,
this.markCalendar(r,f,"dhx_year_event")),f=this.date.add(f,1,"day"),!(f.valueOf()>=o.valueOf())););}}return this._markCalendarCurrentDate(r),r.conf=e,e.sync&&!a&&this._synced_minicalendars.push(r),r.conf._on_xle_handler||(r.conf._on_xle_handler=scheduler.attachEvent("onXLE",function(){scheduler.updateCalendar(r,r.conf.date)})),r},scheduler._get_def_cont=function(e){return this._def_count||(this._def_count=document.createElement("DIV"),this._def_count.className="dhx_minical_popup",this._def_count.onclick=function(e){
(e||event).cancelBubble=!0},document.body.appendChild(this._def_count)),this._def_count.style.left=e.left+"px",this._def_count.style.top=e.top+"px",this._def_count._created=new Date,this._def_count},scheduler._locateCalendar=function(e,t){if("string"==typeof t&&(t=scheduler.templates.api_date(t)),+t>+e._max_date||+t<+e._min_date)return null;for(var a=e.querySelector(".dhx_year_body").childNodes[0],r=0,i=new Date(e._min_date);+this.date.add(i,1,"week")<=+t;)i=this.date.add(i,1,"week"),r++;var n=scheduler.config.start_on_monday,s=(t.getDay()||(n?7:0))-(n?1:0);
return a.rows[r].cells[s].firstChild},scheduler.markCalendar=function(e,t,a){var r=this._locateCalendar(e,t);r&&(r.className+=" "+a)},scheduler.unmarkCalendar=function(e,t,a){if(t=t||e._last_date,a=a||"dhx_calendar_click",t){var r=this._locateCalendar(e,t);r&&(r.className=(r.className||"").replace(RegExp(a,"g")))}},scheduler._week_template=function(e){for(var t=e||250,a=0,r=document.createElement("div"),i=this.date.week_start(scheduler._currentDate()),n=0;7>n;n++)this._cols[n]=Math.floor(t/(7-n)),
this._render_x_header(n,a,i,r),i=this.date.add(i,1,"day"),t-=this._cols[n],a+=this._cols[n];return r.lastChild.className+=" dhx_scale_bar_last",r},scheduler.updateCalendar=function(e,t){e.conf.date=t,this.renderCalendar(e.conf,e,!0)},scheduler._mini_cal_arrows=["&nbsp","&nbsp"],scheduler._render_calendar=function(e,t,a,r){var i=scheduler.templates,n=this._cols;this._cols=[];var s=this._mode;this._mode="calendar";var d=this._colsS;this._colsS={height:0};var l=new Date(this._min_date),o=new Date(this._max_date),_=new Date(scheduler._date),c=i.month_day,h=this._ignores_detected;
this._ignores_detected=0,i.month_day=i.calendar_date,t=this.date.month_start(t);var u,v=this._week_template(e.offsetWidth-1-this.config.minicalendar.padding);r?u=r:(u=document.createElement("DIV"),u.className="dhx_cal_container dhx_mini_calendar"),u.setAttribute("date",this.templates.xml_format(t)),u.innerHTML="<div class='dhx_year_month'></div><div class='dhx_year_grid'><div class='dhx_year_week'>"+(v?v.innerHTML:"")+"</div><div class='dhx_year_body'></div></div>";var f=u.querySelector(".dhx_year_month"),g=u.querySelector(".dhx_year_week"),m=u.querySelector(".dhx_year_body");
if(f.innerHTML=this.templates.calendar_month(t),a.navigation)for(var p=function(e,t){var a=scheduler.date.add(e._date,t,"month");scheduler.updateCalendar(e,a),scheduler._date.getMonth()==e._date.getMonth()&&scheduler._date.getFullYear()==e._date.getFullYear()&&scheduler._markCalendarCurrentDate(e)},y=["dhx_cal_prev_button","dhx_cal_next_button"],b=["left:1px;top:2px;position:absolute;","left:auto; right:1px;top:2px;position:absolute;"],x=[-1,1],w=function(e){return function(){if(a.sync)for(var t=scheduler._synced_minicalendars,r=0;r<t.length;r++)p(t[r],e);else p(u,e);
}},k=[scheduler.locale.labels.prev,scheduler.locale.labels.next],E=0;2>E;E++){var D=document.createElement("DIV");D.className=y[E],scheduler._waiAria.headerButtonsAttributes(D,k[E]),D.style.cssText=b[E],D.innerHTML=this._mini_cal_arrows[E],f.appendChild(D),D.onclick=w(x[E])}u._date=new Date(t),u.week_start=(t.getDay()-(this.config.start_on_monday?1:0)+7)%7;var N=u._min_date=this.date.week_start(t);u._max_date=this.date.add(u._min_date,6,"week"),this._reset_month_scale(m,t,N,6),r||e.appendChild(u),
g.style.height=g.childNodes[0].offsetHeight-1+"px";var S=scheduler.uid();scheduler._waiAria.minicalHeader(f,S),scheduler._waiAria.minicalGrid(u.querySelector(".dhx_year_grid"),S),scheduler._waiAria.minicalRow(g);for(var A=g.querySelectorAll(".dhx_scale_bar"),M=0;M<A.length;M++)scheduler._waiAria.minicalHeadCell(A[M]);for(var C=m.querySelectorAll("td"),O=new Date(l),M=0;M<C.length;M++)scheduler._waiAria.minicalDayCell(C[M],new Date(O)),O=scheduler.date.add(O,1,"day");return scheduler._waiAria.minicalHeader(f,S),
this._cols=n,this._mode=s,this._colsS=d,this._min_date=l,this._max_date=o,scheduler._date=_,i.month_day=c,this._ignores_detected=h,u},scheduler.destroyCalendar=function(e,t){!e&&this._def_count&&this._def_count.firstChild&&(t||(new Date).valueOf()-this._def_count._created.valueOf()>500)&&(e=this._def_count.firstChild),e&&(e.onclick=null,e.innerHTML="",e.parentNode&&e.parentNode.removeChild(e),this._def_count&&(this._def_count.style.top="-1000px"),e.conf&&e.conf._on_xle_handler&&scheduler.detachEvent(e.conf._on_xle_handler));
},scheduler.isCalendarVisible=function(){return this._def_count&&parseInt(this._def_count.style.top,10)>0?this._def_count:!1},scheduler._attach_minical_events=function(){dhtmlxEvent(document.body,"click",function(){scheduler.destroyCalendar()}),scheduler._attach_minical_events=function(){}},scheduler.attachEvent("onTemplatesReady",function(){scheduler._attach_minical_events()}),scheduler.templates.calendar_time=scheduler.date.date_to_str("%d-%m-%Y"),scheduler.form_blocks.calendar_time={render:function(e){
var t="<input class='dhx_readonly' type='text' readonly='true'>",a=scheduler.config,r=this.date.date_part(scheduler._currentDate()),i=1440,n=0;a.limit_time_select&&(n=60*a.first_hour,i=60*a.last_hour+1),r.setHours(n/60),e._time_values=[],t+=" <select>";for(var s=n;i>s;s+=1*this.config.time_step){var d=this.templates.time_picker(r);t+="<option value='"+s+"'>"+d+"</option>",e._time_values.push(s),r=this.date.add(r,this.config.time_step,"minute")}t+="</select>";scheduler.config.full_day;return"<div style='height:30px;padding-top:0; font-size:inherit;' class='dhx_section_time'>"+t+"<span style='font-weight:normal; font-size:10pt;'> &nbsp;&ndash;&nbsp; </span>"+t+"</div>";
},set_value:function(e,t,a,r){function i(e,t,a){_(e,t,a),e.value=scheduler.templates.calendar_time(t),e._date=scheduler.date.date_part(new Date(t))}function n(e){for(var t=r._time_values,a=60*e.getHours()+e.getMinutes(),i=a,n=!1,s=0;s<t.length;s++){var d=t[s];if(d===a){n=!0;break}a>d&&(i=d)}return n||i?n?a:i:-1}var s,d,l=e.getElementsByTagName("input"),o=e.getElementsByTagName("select"),_=function(e,t,a){e.onclick=function(){scheduler.destroyCalendar(null,!0),scheduler.renderCalendar({position:e,
date:new Date(this._date),navigation:!0,handler:function(t){e.value=scheduler.templates.calendar_time(t),e._date=new Date(t),scheduler.destroyCalendar(),scheduler.config.event_duration&&scheduler.config.auto_end_date&&0===a&&v()}})}};if(scheduler.config.full_day){if(!e._full_day){var c="<label class='dhx_fullday'><input type='checkbox' name='full_day' value='true'> "+scheduler.locale.labels.full_day+"&nbsp;</label></input>";scheduler.config.wide_form||(c=e.previousSibling.innerHTML+c),e.previousSibling.innerHTML=c,
e._full_day=!0}var h=e.previousSibling.getElementsByTagName("input")[0],u=0===scheduler.date.time_part(a.start_date)&&0===scheduler.date.time_part(a.end_date);h.checked=u,o[0].disabled=h.checked,o[1].disabled=h.checked,h.onclick=function(){if(h.checked===!0){var t={};scheduler.form_blocks.calendar_time.get_value(e,t),s=scheduler.date.date_part(t.start_date),d=scheduler.date.date_part(t.end_date),(+d==+s||+d>=+s&&(0!==a.end_date.getHours()||0!==a.end_date.getMinutes()))&&(d=scheduler.date.add(d,1,"day"));
}var r=s||a.start_date,n=d||a.end_date;i(l[0],r),i(l[1],n),o[0].value=60*r.getHours()+r.getMinutes(),o[1].value=60*n.getHours()+n.getMinutes(),o[0].disabled=h.checked,o[1].disabled=h.checked}}if(scheduler.config.event_duration&&scheduler.config.auto_end_date){var v=function(){s=scheduler.date.add(l[0]._date,o[0].value,"minute"),d=new Date(s.getTime()+60*scheduler.config.event_duration*1e3),l[1].value=scheduler.templates.calendar_time(d),l[1]._date=scheduler.date.date_part(new Date(d)),o[1].value=60*d.getHours()+d.getMinutes();
};o[0].onchange=v}i(l[0],a.start_date,0),i(l[1],a.end_date,1),_=function(){},o[0].value=n(a.start_date),o[1].value=n(a.end_date)},get_value:function(e,t){var a=e.getElementsByTagName("input"),r=e.getElementsByTagName("select");return t.start_date=scheduler.date.add(a[0]._date,r[0].value,"minute"),t.end_date=scheduler.date.add(a[1]._date,r[1].value,"minute"),t.end_date<=t.start_date&&(t.end_date=scheduler.date.add(t.start_date,scheduler.config.time_step,"minute")),{start_date:new Date(t.start_date),
end_date:new Date(t.end_date)}},focus:function(e){}},scheduler.linkCalendar=function(e,t){var a=function(){var a=scheduler._date,r=new Date(a.valueOf());return t&&(r=t(r)),r.setDate(1),scheduler.updateCalendar(e,r),!0};scheduler.attachEvent("onViewChange",a),scheduler.attachEvent("onXLE",a),scheduler.attachEvent("onEventAdded",a),scheduler.attachEvent("onEventChanged",a),scheduler.attachEvent("onAfterEventDelete",a),a()},scheduler._markCalendarCurrentDate=function(e){var t=scheduler._date,a=scheduler._mode,r=scheduler.date.month_start(new Date(e._date)),i=scheduler.date.add(r,1,"month");
if("day"==a||this._props&&this._props[a])r.valueOf()<=t.valueOf()&&i>t&&scheduler.markCalendar(e,t,"dhx_calendar_click");else if("week"==a)for(var n=scheduler.date.week_start(new Date(t.valueOf())),s=0;7>s;s++)r.valueOf()<=n.valueOf()&&i>n&&scheduler.markCalendar(e,n,"dhx_calendar_click"),n=scheduler.date.add(n,1,"day")},scheduler.attachEvent("onEventCancel",function(){scheduler.destroyCalendar(null,!0)});
// # sourceMappingURL=../sources/ext/dhtmlxscheduler_minical.js.map
