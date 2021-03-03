module.exports = {
  devServer: {
    proxy: {
      "/api": {
        target: "https://web.bear0901.cn:32443/blog",
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    },
    disableHostCheck: true
  }
};
