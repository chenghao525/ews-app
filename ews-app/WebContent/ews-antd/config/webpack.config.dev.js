const config = require("./config");
const merge = require("webpack-merge");
const webpack = require("webpack");
const HtmlPluginWebpack = require("html-webpack-plugin");
const CompressPlugins = require("compression-webpack-plugin");

module.exports = merge(config, {
    mode: "development",
    devtool: "inline-source-map",

    devServer: {
        port: 8089,
        open: false,
        historyApiFallback: true,
        hot: true,
        proxy: {},
        disableHostCheck: true,
        host: '0.0.0.0'
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        // new CompressPlugins(),
        new HtmlPluginWebpack({
            template: './src/dev.html',
            filename: 'index.html',
            minify: {
                collapseWhitespace: true,
                removeComments: true,
            },
            hash: true
        })
    ]
});
