function order(orderData) {
    let uid
    $.ajax({
        url: "/order",
        method: "POST",
        contentType: "application/json",
        async: false,
        data: JSON.stringify(orderData)
    }).done(function (data) {
        console.log("order done:", data)
        uid = data.merchantUid
    }).fail(function (data) {
        console.log("order fail:", data)
    })

    return uid
}