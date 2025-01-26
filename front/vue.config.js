const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {
        target: 'http://192.168.30.129:8848',
        changeOrigin: true
      }
    }
  },
  configureWebpack: {
    // Feature flag VUE_PROD_HYDRATION_MISMATCH_DETAILS is not explicitly defined
    plugins: [
      new (require('webpack').DefinePlugin)({
        __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: JSON.stringify(false)
      })
    ]
  }
})
