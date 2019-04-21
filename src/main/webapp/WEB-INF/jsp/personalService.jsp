<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.lang.String" %>
<%@ page import="org.banking.banking.entities.*" %>
<%--
  Created by IntelliJ IDEA.
  User: wrros
  Date: 4/4/2019
  Time: 12:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
    $(document).ready(function () {
        /*function for show and hide form for creating account*/
        $('#createAccount').click(function () {
            $('.createAccount').toggle();
        });
        $('#showAccounts').click(function () {
            $('.showAccount').toggle();
        });

        $('#replenishmentButton').click(function () {
            $('.replenishment').toggle();
        });
        $('#withdrawButton').click(function () {
            $('.withdraw').toggle();
        });
        $('#transferringMoney').click(function () {
            $('.transferMoney').toggle();

        });
        $('#changingCurrency').click(function () {
            $('.changeCurrency').toggle();

        });

            /*method for sending form with class .sendForm to backend*/
        $(".sendForm")
            .submit(function (e) {
            e.preventDefault(); // avoid to execute the actual submit of the form.
            var form = $(this);
            var url = form.attr('action');
            $.ajax({
                type: "POST",
                url: url,
                data: form.serialize(), // serializes the form's elements.
                success: function (data) {
                    alert(data);
                }
            });
        });

    });

</script>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%--    <link rel="stylesheet" href="css/myform.css"/>--%>
<%--    <link rel="stylesheet" href="css/app.css"/>--%>

    <title>Personal Service</title>
    <style type="text/css">
        body { margin: 10px;

            background-color: #4c2a4b;
        }
        #sidebar, #content { position: absolute; }
        #sidebar, #content { overflow: auto; padding: 20px; }
        #header {
            height: 350px; / Высота слоя /
        background: #FEDFC0;
            /*border-bottom: 2px solid #7B5427;*/
        }
        #header h1 {
            padding: 5px; margin: 0px;
            height: 100px;
            background: cornflowerblue;
            text-align: center;
            color: bisque;
        border: 1px solid #7B5427}

        #header h2 {
            padding: 5px; margin: 0px;
            height: 100px;
            background: cornflowerblue;
            text-align: center;
            color: bisque;
            border: 1px solid #7B5427}

        #sidebar {
            height: 450px;
            width: 400px; background: #e5eada; ;
            /*border: 2px solid #7B5427;*/
            top: 102px; / Расстояние от верхнего края /

        bottom: 10px; / Расстояние снизу  /

        }
        #content {
            height: 450px;
            top: 102px;
            left: 375px; / Расстояние от левого края /
        bottom: 10px; right: 12px;
            background: #b9baae;
            /*border: 2px solid #7B5427*/
        }
    </style>

</head>
<body>
<div

        id="header"><h1>Personal Service<br>

  ${customer.firstName} ${customer.lastName}</h1>
</div>





<%--<----------------------- Sidebar ------------------------------------------>--%>

<div id="sidebar">

    <%--<-----------------------Button "Show Accounts" ------------------------------------------>--%>

        <input type="button" value="Show accounts " id="showAccounts"  />
    <div class="showAccount" style="display:none">
        <br>

            <c:forEach items="${customer.accounts}" var="account">
                <div>${account.currency} : ${account.name} : ${String.format("%.2f", account.amount)}</div>
<%--                <div>${account.name}</div>--%>
<%--                <div>${account.amount}</div>--%>
            </c:forEach>

        </div>


<br><br><br>

        <%--<-------------------- Button "Create New Account" ------------------>--%>

        <input type="button" value="Create New Account " id="createAccount"  />

    <div class="createAccount" style="display:none">
        <br>
        <form class="sendForm"
                method="post" action="${pageContext.request.contextPath}/createAccount">
            <lable>Select currency:
            </lable>
            <br>
            <select name="currency">
                <c:forEach items="<%=org.banking.banking.entities.Currency.values()%>"
                           var="currency">
                    <option value="${currency}">
                        ${currency}
                    </option>

                </c:forEach>
<%--                <option value="USD">USD</option>--%>
<%--                <option value="RUR">RUR</option>--%>
            </select>
            <br>
            <label>Account Name:</label>
            <input type="text" name="name">
            <input type="hidden" name="customerId" value="${customer.id}">
            <input type="submit" name="submit">
        </form>

    </div>
    <br><br><br>

        <%--<------------- Button "Add Money to Your Account" ---------------->--%>

    <input type="button" value="Add Money to Your Account " id="replenishmentButton"  />
    <div class="replenishment" style="display:none">
        <br>
        <form class="sendForm"
              method="post" action="${pageContext.request.contextPath}/replenishment">
            <lable>Select account:
            </lable>
            <br>
            <select name="accountId">
                <c:forEach items="${customer.accounts}" var="account">
                    <option value="${account.id}">
                            ${account.name}
                    </option>
                </c:forEach>
            </select>

            <br>
            <lable>Amount:</lable>
            <input type="text" name="amount">
            <input type="submit" name="submit">
        </form>

    </div>

    <br><br><br>

        <%--<------------- Button "Withdraw Money from Your Account" -------------->--%>

        <input type="button" value="Withdraw Money from Your Account" id="withdrawButton"  />
    <div class="withdraw" style="display:none">
        <br>
        <form class="sendForm"
              method="post" action="${pageContext.request.contextPath}/withdrawMoney">
            <lable>Select account:
            </lable>
            <br>
            <select name="accountId">
                <c:forEach items="${customer.accounts}" var="account">
                    <option value="${account.id}">
                            ${account.name}
                    </option>
                </c:forEach>
            </select>

            <br>
            <lable>Amount:</lable>
            <input type="text" name="amount">
            <input type="submit" name="submit">
        </form>

    </div>
    <br><br>

        <%--<------------- Button "Transfer Money " -------------->--%>

 <input type="button"
        value="Send Money" id="transferringMoney"/>
    <br>
    <div class="transferMoney" style="display:none">
        <form class="sendForm" method="post"
              action="${pageContext.request.contextPath}/transferringMoney">

            <label>Account From:</label>
            <br>
            <select name="accountIdFrom">
                <c:forEach items="${customer.accounts}" var="account">
                    <option value="${account.id}">
                        ${account.name}
                    </option>
                </c:forEach>
            </select>
            <br>
            <label>Account to:</label>
            <br>
            <select name="accountIdTo">
                <c:forEach items="${customers}" var="customer">
                    <optgroup label="${customer.firstName} ${customer.lastName}">
                        <c:forEach items="${customer.accounts}" var="account">
                            <option value="${account.id}">
                                    ${account.name}
                            </option>
                        </c:forEach>
                    </optgroup>

                </c:forEach>
            </select>

            <br><br>
            <label>Amount</label>
            <input type="text" name="amount">
            <input type="submit" name="submit">
        </form>
    </div>
        <br><br>
        <%--<------------- Button "Change Currency " -------------->--%>
        <input type="button"
               value="Change Currency" id="changingCurrency"/>
        <br>
        <div class="changeCurrency" style="display:none">
            <form class="sendForm" method="post"
                  action="${pageContext.request.contextPath}/changingCurrency">

                <label>Account From:</label>
                <br>
                <select name="accountIdToChange">
                    <c:forEach items="${customer.accounts}" var="account">
                        <option value="${account.id}">
                                ${account.name}
                        </option>
                    </c:forEach>
                </select>
                <br>
                <label>Account to:</label>
                <br>
                <select name="accountIdFromChange">
                            <c:forEach items="${customer.accounts}" var="account">
                                <option value="${account.id}">
                                        ${account.name}
                                </option>
                            </c:forEach>

                </select>

                <br>
                <label>Amount</label>
                <input type="text" name="amount">
                <input type="submit" name="submit">
            </form>
        </div>
        <br><br>
        <a href="${pageContext.request.contextPath}/">Home</a>
</div>


</div>



<%--<------------- Context Field" -------------->--%>

<div id="content">

        <c:set var="dataPattern" value="${DateTimeFormatter.ofPattern('dd-MM-yyyy HH:mm')}">

        </c:set>

    <c:forEach items="${transactions}" var="transaction">
        <c:if test="${transaction['class'].name.equals('org.banking.banking.entities.Replenishment')}">
            <div style="color: #5e6057">
            Replenishment :
                ${transaction.dateTime.format(dataPattern)} :
                ${customer.firstName} ${customer.lastName} :

                    <%= ((Replenishment) pageContext.getAttribute("transaction")).getAccount().getName()%>
                <font color="green">+ ${transaction.value }</font>
                <%--<div style="color: green">  + ${transaction.value } </div>--%>



        </div>
    </c:if>
        <c:if test="${transaction['class'].name.equals('org.banking.banking.entities.Withdrawal')}">
            <div style="color: #6c705e">
                Withdrawal :
                    ${transaction.dateTime.format(dataPattern)} :
                    ${customer.firstName} ${customer.lastName} :
           <%=((Withdrawal)pageContext.getAttribute("transaction")).getAccount().getName()%>
                <font color="red"> - ${transaction.value } </font>
            </div>
        </c:if>


        <c:if test="${transaction['class'].name.equals('org.banking.banking.entities.Transfer')}">
            <div style="color: #6c705e">
                   Transfer :
                    ${transaction.dateTime.format(dataPattern)}  from :
                        <%=((Transfer)pageContext.getAttribute("transaction")).getAccountFrom().getCustomer().getFirstName()%> :
                        <%= ((Transfer) pageContext.getAttribute("transaction")).getAccountFrom().getName()%>
                <font color="red"> - ${transaction.value } </font>  to :
                <%=((Transfer)pageContext.getAttribute("transaction")).getAccountTo().getCustomer().getFirstName()%> :
                <%=((Transfer) pageContext.getAttribute("transaction")).getAccountTo().getName()%>
            <font color="green"> + ${transaction.value}</font>

            </div>
        </c:if>


        <c:if test="${transaction['class'].name.equals('org.banking.banking.entities.ChangingCurrency')}">
            <div style="color: #6c705e">
                Changing :
                    ${transaction.dateTime.format(dataPattern)}  :
                <%=((ChangingCurrency)pageContext.getAttribute("transaction")).getAccountToChange().getCurrency()%>
                <%=((ChangingCurrency)pageContext.getAttribute("transaction")).getAccountToChange().getCustomer().getFirstName()%> :
                <%= ((ChangingCurrency) pageContext.getAttribute("transaction")).getAccountFromChange().getName()%>
                <font color="red"> - ${transaction.value } </font>
                <%=((ChangingCurrency)pageContext.getAttribute("transaction")).getAccountToChange().getCurrency()%>
                to :
                <%=((ChangingCurrency)pageContext.getAttribute("transaction")).getAccountFromChange().getCustomer().getFirstName()%> :
                <%=((ChangingCurrency) pageContext.getAttribute("transaction")).getAccountFromChange().getName()%>
                <font color="green"> + ${transaction.value}</font>
                <%=((ChangingCurrency)pageContext.getAttribute("transaction")).getAccountFromChange().getCurrency()%>

            </div>
        </c:if>
    </c:forEach>




</div>

</body>
</html>
