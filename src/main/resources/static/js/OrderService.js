class OrderService {
    constructor(orderButtonId) {
        let IMP = window.IMP
        IMP.init(this.constructor.IMP_ID)
    }

    iamportOrder(a, b) {
        let merchant_uid = "testUid"
        let thisObj = this
        IMP.request_pay({ // param
            pg: thisObj.constructor.PG_NAME,
            pay_method: "card",
            merchant_uid: merchant_uid,
            name: "노르웨이 회전 의자",
            amount: 1000,
            buyer_email: "gildong@gmail.com",
            buyer_name: "홍길동",
            buyer_tel: "010-4242-4242",
            buyer_addr: "서울특별시 강남구 신사동",
            buyer_postcode: "01181"
        }, function (rsp) { // callback
            if (rsp.success) {
                thisObj.successProcess()
            } else {
                thisObj.failProcess()
            }
        });
    }

    successProcess() {
        console.log("successProcess")
    }

    failProcess() {
        console.log("failProcess")
    }
}

OrderService.PG_NAME = "html5_inicis"
OrderService.IMP_ID = "imp44441390"