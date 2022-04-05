const path = require('path')
module.exports = {
  configureWebpack:{
      resolve:{
          alias:{//别名
            '@': path.resolve(__dirname,'./src'),
            'assets': path.resolve(__dirname,'./src/assets'),
            'components': path.resolve(__dirname,'./src/components'),
            'api': path.resolve(__dirname,'./src/api'),
            'views': path.resolve(__dirname,'./src/views'),
          }
      }
  },
  publicPath: '/',
  //关闭eslint
  lintOnSave: false,
  devServer: {
      port:8081,
      overlay: {
          warning: false,
          errors: false
      }
  },
}