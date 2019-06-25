<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script type="text/javascript" src="/js/jquery-3.4.0.min.js"></script>
<script type="text/javascript" src="/js/popper.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<style type="text/css">
	* {
		width: 100%;
	}
	.box_c {
		float: left;
		margin: 2%;
		width: 25%;
	}

</style>

<script type="text/javascript">
	$(function () {
		var ctx = "${pageContext.request.contextPath}";
		console.log(ctx);
		/* $.post("/api/v1/lasttransactions", null, function (data) {
            console.log('data ' + JSON.stringify(data));
            var str = '';
            if (data.result.txs && data.result.txs.length > 0) {
                for (var i = 0; i < data.result.txs.length; i++) {
                    var fee = data.result.txs[i].fee == 0 ? '-' : data.result.txs[i].fee.toFixed(8);
                    str += '<tr><td scope="row">' + data.result.txs[i].uid + '</td>'
                        + '<td>' + data.result.txs[i].category + '</td>'
                        + '<td class="ellipsis"><a target="_blank" href="https://live.blockcypher.com/btc-testnet/tx/'
                        + data.result.txs[i].txid + '">' + data.result.txs[i].txid + '</a></td>'
                        + '<td>' + data.result.txs[i].toAddr + '</td>'
                        + '<td>' + data.result.txs[i].amount.toFixed(8) + '</td>'
                        + '<td>' + fee + '</td>'
                        + '<td>' + data.result.txs[i].confirm + '</td>'
                        + '<td>' + data.result.txs[i].txTimeStr + '</td></tr>';
                }
                $("#txs").html(str);
            }
            $("#transaction-count").text(data.result.totalCount);
        }); */
		
		$.ajax({
			url : "/goods",
			type : "POST",
			dataType : "json",
			async : false,
			data : {},
			success : function(data) {
				console.log(data);
			}
		});
	});
	
</script>

<title>Insert title here</title>
</head>
<body>
	<div>
	<div class="box_c"></div>
	</div>
</body>
</html>