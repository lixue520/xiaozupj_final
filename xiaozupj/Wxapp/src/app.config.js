export default defineAppConfig({
  pages: [
    'pages/index/index'
  ],
  window: {
    backgroundTextStyle: 'light',
    navigationBarBackgroundColor: '#fff',
    navigationBarTitleText: '我的咨询',
    navigationBarTextStyle: 'black'
  },
  subPackages: [
    {
      "root": "pages/OrderDishes/",
      "pages": [
        "index"
      ]
    },
    {
      "root": "pages/OrderForm/",
      "pages": [
        "index"
      ]
    },
    {
      "root": "pages/Mine/",
      "pages": [
        "index",
        "saving",
        "ticket/index",
        "integral",
        "code",
        "members",
        "invite",
        "help",
        "profile",
        "login"
      ]
    },
    {
      "root" :"pages/address",
      "pages": [
          "index",
          "insertAdd"
      ]
    }
  ]
})
