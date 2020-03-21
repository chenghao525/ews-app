const path = require("path");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = {
    entry: {
        index: path.resolve("./src/index.js"),
        vendor: ['react', 'react-dom']
    },
    output: {
        filename: "[name].js",
        path: path.resolve("dist")
    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                use: "babel-loader"
            },
            {
                test: /\.(c|le|sc|sa)ss$/,
                use: [MiniCssExtractPlugin.loader, 'css-loader', 'less-loader']
            },
            // {
            //     test: /\.less$/,
            //     include: path.resolve(__dirname, 'node_modules/antd'),
            //     use: [MiniCssExtractPlugin.loader, 'css-loader', 'less-loader'],
            // },
            {
                test: /\.(woff|ttf|svg|eot|woff2)$/,
                use: ['url-loader'],
                exclude: /node_modules/
            },
            {
                test: /\.(png|jpe?g|gif)$/,
                use: [
                    {
                        loader: 'url-loader',
                        options:{
                            limit: 1024, // 将小于1024的图片打包成Base64的形式放入JS中
                            name: '/src/assets/[name].[ext]'
                        }
                    }
                ]
            },
            {
                test: /\.ts$/,
                use: ['ts-loader']
            }
        ]
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: 'css/[name].css',
            chunkFilename: 'css/[id].css'
        })
    ]
}
