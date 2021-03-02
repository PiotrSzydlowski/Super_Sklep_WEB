let cart;
let products;

function add_to_cart(pid, pname, pprice) {
    cart = localStorage.getItem("cart");
        if (cart == null) {
            //no cart yet
            products = [];
            let product = {productId: pid, productName: pname, productQuantity: 1, productPrice: pprice};
            products.push(product);
            localStorage.setItem("cart", JSON.stringify(products));
            console.log("product dodany po raz pierwszy")
            showToast(product.productName + " Dodano produkt do koszyka")
        } else {
            //cart is already present
            let pcart = JSON.parse(cart);

            let oldProduct = pcart.find((item) => item.productId == pid)
            if (oldProduct) {
                //increase quantity
                oldProduct.productQuantity = oldProduct.productQuantity + 1
                pcart.map((item) => {
                    if (item.productId === oldProduct.productId) {
                        item.productQuantity = oldProduct.productQuantity;
                    }
                })
                localStorage.setItem("cart", JSON.stringify(pcart));
                console.log("Ilośc zwiększona")
                showToast("Zwiększono ilość " + oldProduct.productName + " w koszyku")
            } else {
                //add product
                let product = {productId: pid, productName: pname, productQuantity: 1, productPrice: pprice};
                pcart.push(product);
                localStorage.setItem("cart", JSON.stringify(pcart));
                console.log("produkt dodany")
                showToast("Produkt dodany do koszyka")
            }
        }
        updateCart();
}

//update cart
function updateCart() {
    let finalCart = [];
    let finalQnt = [];
    let cartString = localStorage.getItem("cart");
    let cart = JSON.parse(cartString);
    if (cart == null || cart.length == 0) {
        console.log("Koszyk jest pusty !!")
        $(".cart-items").html("( 0 )");
        $(".cart-body").html("<h3>Koszyk pusty</h3>");
        $(".checkout-btn").attr('disabled', true);
    } else {
        // console.log(cart)
        $(".cart-items").html(cart.length);
        let table =
            '<table class="table"><thead class = "thread-light"><tr>' +
            '<th>Nazwa Produktu</th>' +
            '<th>Cena</th>' +
            '<th>Ilość</th>' +
            '<th>Suma</th>' +
            '<th>Akcja</th>' +
            '</tr>' +
            '</thead>';
        let totalPrice = 0;
        cart.map((item) => {
            table += '<tr>' +
                '<td>' + item.productName + '</td>' +
                '<td>' + item.productPrice + '</td>' +
                '<td>' + item.productQuantity + '</td>' +
                '<td>' + item.productQuantity * item.productPrice + 'zł' + '</td>' +
                '<td><button onclick="deleteProductFromCart(' + item.productId + ')" class="btn btn-danger btn-sm">Usuń</button></td>' +
                '</tr>'
            totalPrice += item.productPrice * item.productQuantity;

            finalCart.push(item.productId);
            finalQnt.push(item.productQuantity);

            let cookiesId1 = "productId1 =" + finalCart[0];
            let cookiesId2 = "productId2 =" + finalCart[1];
            let cookiesId3 = "productId3 =" + finalCart[2];

            let cookiesQnt1 = "productQnt1 =" + finalQnt[0];
            let cookiesQnt2 = "productQnt2 =" + finalQnt[1];
            let cookiesQnt3 = "productQnt3 =" + finalQnt[2];

            let totalPriceCookie = "totalPrice =" + totalPrice;

            document.cookie = cookiesQnt1;
            document.cookie = cookiesQnt2;
            document.cookie = cookiesQnt3;
            document.cookie = cookiesId1;
            document.cookie = cookiesId2;
            document.cookie = cookiesId3;
            document.cookie = totalPriceCookie;

        })
        table = table +
            '<tr><td class="text-right" style="font-weight: bold" colspan="5">Do zapłaty: ' + totalPrice + ' zł' + '</td></tr>' +
            '</table>'
        $(".cart-body").html(table);
        $(".checkout-btn").attr('disabled', false);
    }
}

function deleteProductFromCart(pid) {
    let cart = JSON.parse(localStorage.getItem('cart'));
    let newCart = cart.filter((item) => item.productId != pid)
    localStorage.setItem('cart', JSON.stringify(newCart))
    updateCart();
    showToast("Usunięto produkt z koszyka")
}

$(document).ready(function () {
    updateCart();
})

function showToast(content) {
    $("#toast").addClass("display");
    $("#toast").html(content);
    setTimeout(() => {
        $("#toast").removeClass("display");
    }, 2000)
}

function goToCheckOut() {
    window.location = "checkout.jsp";
}

function deleteCart() {

}





