<!-- 
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
 -->
 
<jsp:include page="/WEB-INF/pages/segments/header.jsp" />
<jsp:include page="/WEB-INF/pages/segments/navbar.jsp" />
<h1>Order</h1>

<!-- Apple iPhone 12  -->
<div class="card" style="width: 18rem;">
  <img class="card-img-top" src="https://www.theiphonewiki.com/w/images/3/3a/IPhone_12.jpg" alt="iPhone 12">
  <div class="card-body">
    <h5 class="card-title">Apple iPhone 12</h5>
    <p class="card-text">Nice body, sexy screen, sharp graphics</p>
  </div>
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Brand: Apple</li>
    <li class="list-group-item">Model: iPhone 12 (D53GAP)</li>
    <li class="list-group-item">Price: $1399.99</li>
  </ul>
  <div class="card-body">
    <a href="#" class="card-link">Add to Order</a>
    <button class="btn-primary">Add to Order</button>
  </div>
</div>

<!-- Samsung Galaxy S20 -->

<div class="card" style="width: 18rem;">
  <img class="card-img-top" src="https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRAsQ7xJq4XqkiZPZaGYFmLEsw87xmDp8XckGNzlDOOaTc9YeUXiQ&usqp=CAc" alt="iPhone 12">
  <div class="card-body">
    <h5 class="card-title">Samsung Galaxy 20</h5>
    <p class="card-text">Also has a nice body, sexy screen, sharp graphics</p>
  </div>
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Brand: Samsung</li>
    <li class="list-group-item">Model: Galaxy S20</li>
    <li class="list-group-item">Price: $1399.99</li>
  </ul>
  <div class="card-body">
    <a href="#" class="card-link">Add to Order</a>
  </div>
</div>

<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />