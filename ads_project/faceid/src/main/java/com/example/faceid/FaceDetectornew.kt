import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.gpu.GpuDelegate
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class FaceDetectornew(context: Context) {
    private lateinit var interpreter: Interpreter

    init {
        // Khởi tạo và load mô hình nhận diện khuôn mặt
//        val model = FileUtil.loadMappedFile(context, "face_model.tflite")
        val options = Interpreter.Options()
        options.addDelegate(GpuDelegate())
//        interpreter = Interpreter(model, options)
    }

    fun detectFace(bitmap: Bitmap): Boolean {
        // Tiến hành xử lý quét và so sánh khuôn mặt
        val image = TensorImage.fromBitmap(bitmap)
        val inputShape = interpreter.getInputTensor(0).shape()
        val outputShape = interpreter.getOutputTensor(0).shape()

        // Xử lý input tensor
        val inputBuffer = TensorImage(image.dataType)
        inputBuffer.load(bitmap)

        // Xử lý output tensor
        val outputBuffer = TensorBuffer.createFixedSize(outputShape, DataType.FLOAT32)

        // Chạy mô hình
        interpreter.run(inputBuffer.buffer, outputBuffer.buffer.rewind())

        // Xử lý kết quả và so sánh
        val result = processOutputBuffer(outputBuffer)

        return result
    }

    private fun processOutputBuffer(outputBuffer: TensorBuffer): Boolean {
        // Xử lý kết quả từ output buffer
        // Thực hiện so sánh kết quả với khuôn mặt đã lưu trữ
        // Trả về true nếu khuôn mặt được tìm thấy, ngược lại trả về false
        return false
    }

    fun close() {
        // Đóng Interpreter khi không sử dụng nữa
        interpreter.close()
    }
}