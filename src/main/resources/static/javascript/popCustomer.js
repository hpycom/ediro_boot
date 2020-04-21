
var ediro = {}||ediro;
ediro.popup = {}||ediro.popup;

ediro.popup.PopCustomer = function(valObj) {
	 (function render(_divId)
	  {
		  var popRenderDiv = document.getElementById(_divId);
		  
		  popRenderDiv.innerHTML='<div class="modal" id="__PopModal" data-backdrop="static" tabindex="-1" role="dialog"   aria-hidden="true"> ' +
								  '  <div class="modal-dialog modal-dialog-centered modal-xl"> ' +
								  '	  <div class="modal-content"> ' +
								  '		<div class="modal-header modal-xl"> ' +
								  '        <h5 class="modal-title" id="exampleModalLabel">ediro</h5> ' +
								  '	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> ' +
								  '	          <span aria-hidden="true">&times;</span> ' +
								  '	        </button> ' +
								  '	    </div> ' +
								  '	      <div class="modal-body modal-xl"> ' +
								  '	       <div class="row justify-content-start" >' +
									     
								  '	   <div class="form-group col-md-4"> ' +
								  '       		<input type="text" autocomplete="off" class="form-control form-control-sm" id="__company_name" name="__company_name" placeHolder="서점명" /> ' +
								  '         </div> ' +
								  '  	   <div class="form-group col-md-4"> ' +
								  '       		<input type="text" autocomplete="off" class="form-control form-control-sm" id="__boss_name" name="__boss_name" placeHolder="대표자명" /> ' +
								  '         </div> ' +
								  '           <div class="form-group col-md-2"> ' +
								  '         <button id="__searchBtn">search</button> ' +
								  '         </div> ' +
								  '     </div> ' +
								  '        <div > ' +
								  '       <div id="__divCusSch"></div> ' +
								  '       </div> ' +
								  '      </div> ' +
								  '	      <div class="modal-footer modal-xl"> ' +
								  '	        <button type="button" id="btnCloseMdl" class="btn btn-secondary" data-dismiss="modal">Close</button> ' +
								  '	      </div>  ' +
								  '	    </div> ' +
								  '	  </div> ' +
								  '</div>	' ;
	   })(valObj.divId);                         
	  
	 var hotMemSch;
	 
	 var datahot =  {
			  licenseKey: 'non-commercial-and-evaluation',
			  height:400,
			  rowHeaders: true,
			  colHeaders: true,
			  colHeaders: ['선택','mid', '서점명','대표자명','전화','전화2','팩스','주소', '상세주소','사업자번호'],
			  startRows: 0,
			  startCols: 10,
			  fillHandle:false,
			  currentRowClassName: 'currentRow',
			  manualColumnResize: true,
			  selectionMode: 'single',
			  columns: [
				 			{data: 'chk',type: 'checkbox',checkedTemplate: '1',uncheckedTemplate: '0'},
						    {data: 'mid',readOnly:true},
						    {data: 'companyName',readOnly:true},
						    {data: 'bossName',readOnly:true},
						    {data: 'phone',readOnly:true},
						    {data: 'phone_2',readOnly:true},
						    {data: 'fax',readOnly:true},
						    {data: 'address',readOnly:true},
						    {data: 'address_detail',readOnly:true},
						    {data: 'biz_reg_no',readOnly:true}
						    
						   
				   		],
				hiddenColumns: {
				    columns: [1],
				    indicators: true
				  },
				  afterSelection:function(row, column, row2, column2, preventScrolling, selectionLayerLevel){
			 			 vCol = column;
			 		 	vRow = row;
			 		  },
			 		
			     beforeKeyDown:function(event){
			    	
				    	if(event.keyCode == 13)
				    	{
				    		   
					    		    var data = hotMemSch.getDataAtRow(vRow);
				    				
					    		    if(data.length>0)
				    				{
					    		    	
					    		    	valObj.aftCusSel(data);
						    		    $('#'+'__PopModal').modal('toggle');
						    		    event.stopPropagation();
				    				}
					       		
				    	}
				    	else if(event.keyCode == 32)
			    		{
			    			console.log(event.keyCode);
			    			var chkdata = hotMemSch.getDataAtCell(vRow,0);
			    			
			    			if(chkdata == 0)
			    				hotMemSch.setDataAtCell(vRow, 0, 1);
			    			else
			    				hotMemSch.setDataAtCell(vRow, 0, 0);
			    		}
			     },
			     afterOnCellMouseDown: function(event, coords, td) {
			    	  var now = new Date().getTime();
			    	   // check if dbl-clicked within 1/5th of a second. change 200 (milliseconds) to other value if you want
			    	  if(!(td.lastClick && now - td.lastClick < 200)) {
			    	    td.lastClick = now;
			    	    return; // no double-click detected
			    	  }

			    	  var data = hotMemSch.getDataAtRow(vRow);
	    				
		    		    if(data.length>0)
	    				{
		    		    	
		    		    	valObj.aftCusSel(data);
			    		    $('#'+'__PopModal').modal('toggle');
			    		    event.stopPropagation();
	    				}
			    	}
			 };
	  
				//팝업 도서 목록 가져오기
				  function loadDataMemSch() {
				  	
				  	var sch_company_name = $("input[name=__company_name]").val();
				  	var sch_boss_name = $("input[name=__boss_name]").val();
				  	
				  	var memberVo = {companyName:sch_company_name,bossName:sch_boss_name};
				  	
				  	var token = $("meta[name='_csrf']").attr("content");
				  	var header = $("meta[name='_csrf_header']").attr("content");
				  	//console.log(bookVO);
				  	
				      $.ajax({
				          url: '/data/member/getMember',
				          type: 'get',
				          datatype:'json',
				          data:memberVo,
				          async: true,
				           beforeSend: function( xhr ) {
				          	  xhr.setRequestHeader(header, token);
				          	},
				          success: function(res) {
				          
				          //	console.log(res);
				          	if(res.length > 0)
				          	{
				          		
				          		hotMemSch.loadData(res);
				              	hotMemSch.selectCell(0,0);
				          	}
				          	else
				          	{
				          		
				      		     hotMemSch.updateSettings({
				  				    data : []
				  				});
				      		     $('input[name=__company_name]').focus();
				          		
				          		
				          		 
				          	}
				          }
				      });
				}
				  
		   $('#'+'__PopModal').on('shown.bs.modal', function () {
			    if(hotMemSch != undefined)
			    	hotMemSch.destroy();
				
			    hotMemSch = new Handsontable(__divCusSch,datahot);
	     		loadDataMemSch();
	     	});
		   
		   $("input[name=__company_name]").keydown(function(event) {
				 
			   if(event.keyCode == 13){//키가 13이면 실행 (엔터는 13)
			    	loadDataMemSch();
			        return false;
			   }
			   return true;
			  
			});
			
			$("input[name=__boss_name]").keydown(function(event) {
				 
				  //console.log(event.keyCode);
			    if(event.keyCode == 13){//키가 13이면 실행 (엔터는 13)
			    	loadDataMemSch();
			        return false;
			   }
			   return true;
			  
			});
			
			$("#__searchBtn").click(function(e){
				
				loadDataMemSch();
			});	
			
	if(!(this instanceof ediro.popup.PopCustomer)){
		  return new ediro.popup.PopCustomer(valObj)
		}
	
	
	this.showModal = function(companyName){ $("input[name=__company_name]").val(companyName);
											$('#__PopModal').modal('show');};
};


