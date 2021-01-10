class GuaCanvas extends GuaObject{
    constructor(selector) {
        super()
        this.setup(selector)

    }

    setup(selector) {
        let canvas = _e(selector)
        this.canvas = canvas
        this.context = canvas.getContext('2d')
        this.w = canvas.width
        this.h = canvas.height
        // this.size = this.w * this.h
        this.pixels = this.context.getImageData(0, 0, this.w, this.h)
        // this.bytesPerPixel = 4
        // 格子
        this.data = []
        // this.white = 0b1111111111111111
        // this.black = 0b1111000000000000

    }

    // rgbaFromColor(color) {
    //     let r = ((color >> 0) & 0x0f) * 255 / 0x0f
    //     let g = ((color >> 4) & 0x0f) * 255 / 0x0f
    //     let b = ((color >> 8) & 0x0f) * 255 / 0x0f
    //     let a = ((color >> 12) & 0x0f) * 255 / 0x0f
    //     return [r, g, b, a]
    // }


    __debug_draw_demo() {

        // 这是一个 demo 函数, 用来给你看看如何设置像素
        // ES6 新语法, 取出想要的属性并赋值给变量, 不懂自己搜「ES6 新语法」
        let {context, pixels} = this
        // 获取像素数据，data 是一个数组
        let data = pixels.data
        log('len', data.length)
        // 一个像素 4 字节，分别表示 r g b a
        for (let i = 0; i < data.length; i += 4) {
            let [r, g, b , a] = data.slice(i, i + 4)
            r = 255
            a = 255
            data[i] = r
            data[i+3] = a
        }
        context.putImageData(pixels, 0 , 0)
    }

    drawLine() {

    }

    drawLineHorizontal(y) {
        let w = this.w
        for (let x = 0; x < w; x++) {
            this.drawPoint(x, y)
        }
    }

    drawLineVertical(x) {
        let h = this.h
        for (let y = 0; y < h; y++) {
            this.drawPoint(x, y)
        }
    }

    drawPoint2() {
        // this.drawPoint(10, 10)
        // this.drawPoint(20, 10)
        let {w, h}  = this
        for (let y = 10; y < h; y += 10) {
            this.drawLineHorizontal(y)
        }
        for (let x = 10; x < w; x += 10) {
            this.drawLineVertical(x)
        }
    }


    drawPoint(x, y) {

        let {context, pixels} = this
        let data = pixels.data
        // for (let i = 0; i < data.length; i += 4) {
        //     let [r, g, b , a] = data.slice(i, i + 4)
        //     r = 255
        //     a = 255
        //     data[i] = r
        //     data[i+3] = a
        // }
        var i = x * 4 + y * this.w * 4
        data[i] = 255
        // data[i+1] = 0
        // data[i+2] = 255
        data[i+3] = 255

        context.putImageData(pixels, 0 , 0)
    }

}
