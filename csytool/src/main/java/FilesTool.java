import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Description:
 * https://www.hutool.cn/docs/#/core/IO/文件/文件读取-FileReader
 * <p>
 * //默认UTF-8编码，可以在构造中传入第二个参数做为编码
 * FileReader fileReader = new FileReader("test.properties");
 * String result = fileReader.readString();
 * <p>
 * FileReader提供了以下方法来快速读取文件内容：
 * <p>
 * readBytes
 * readString
 * readLines
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/6/19
 */
public class FilesTool {

    /**
     * 默认编码：UTF-8
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private Charset charset;
    private File file;

    /**
     * 读取文件内容
     *
     * @return 内容
     * @throws IOException IO异常
     */
    public String readString() throws IOException {
        return new String(readBytes(), this.charset);
    }

    /**
     * 读取文件所有数据<br>
     * 文件的长度不能超过 {@link Integer#MAX_VALUE}
     *
     * @return 字节码
     * @throws IOException IO异常
     */
    public byte[] readBytes() throws IOException {
        long len = file.length();

        if (len >= Integer.MAX_VALUE) {
            throw new IOException("File is larger then max array size");
        }

        byte[] bytes = new byte[(int) len];
        FileInputStream in = null;
        int readLength;
        try {
            in = new FileInputStream(file);
            readLength = in.read(bytes);
            if (readLength < len) {
                throw new IOException(String.format("File length is [{}] but read [{}]!", len, readLength));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert in != null;
            in.close();
        }
        return bytes;
    }

    /**
     * 构造
     *
     * @param filePath 文件路径，相对路径会被转换为相对于ClassPath的路径
     * @param charset  编码，使用Charset
     */
    public FilesTool(String filePath, Charset charset) {
        this.file = new File(filePath);
        this.charset = charset;
    }

    /**
     * 构造<br>
     * 编码使用 {@link Charset}
     *
     * @param filePath 文件
     */
    public FilesTool(String filePath) {
        this(filePath, DEFAULT_CHARSET);
    }
}
