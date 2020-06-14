const { createProxyMiddleware } = require('http-proxy-middleware');
module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://localhost:8080',
      changeOrigin: true,
    })
  );
  // Does not work: https://github.com/facebook/create-react-app/issues/5280
  // app.use(
  //   '/ws',
  //   createProxyMiddleware({
  //     target: 'ws://localhost:8080',
  //     ws: true,
  //   })
  // );
};