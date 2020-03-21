const config = require("./config");
const merge = require("webpack-merge");
const { CleanWebpackPlugin } = require("clean-webpack-plugin");
const CompressPlugins = require("compression-webpack-plugin");

module.exports = merge(config, {
    mode: "production",
    performance: {
      hints: false
    },
    plugins: [
        new CleanWebpackPlugin(),
        new CompressPlugins()
    ]
});

