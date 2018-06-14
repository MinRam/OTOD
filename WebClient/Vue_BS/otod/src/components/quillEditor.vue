<template>
  <div style="background-color:white;height:240px">
      <quilleditor v-model="content"
              style="background-color:white;height:200px"
              ref="myTextEditor"
              :options="editorOption"
              @change="onChange">
          <div id="toolbar" slot="toolbar">
            <span class="ql-formats"><button type="button" class="ql-bold"></button></span>
            <span class="ql-formats"><button type="button" class="ql-italic"></button></span>
            <span class="ql-formats"><button type="button" class="ql-underline"></button></span>
            <span class="ql-formats"><select class="ql-color">
              <option selected="selected"></option>
              <option value="#e60000"></option>
              <option value="#ff9900"></option>
              <option value="#ffff00"></option>
              <option value="#008a00"></option>
              <option value="#0066cc"></option>
              <option value="#9933ff"></option>
              <option value="#ffffff"></option>
              <option value="#facccc"></option>
              <option value="#ffebcc"></option>
              <option value="#ffffcc"></option>
              <option value="#cce8cc"></option>
              <option value="#cce0f5"></option>
              <option value="#ebd6ff"></option>
              <option value="#bbbbbb"></option>
              <option value="#f06666"></option>
              <option value="#ffc266"></option>
              <option value="#ffff66"></option>
              <option value="#66b966"></option>
              <option value="#66a3e0"></option>
              <option value="#c285ff"></option>
              <option value="#888888"></option>
              <option value="#a10000"></option>
              <option value="#b26b00"></option>
              <option value="#b2b200"></option>
              <option value="#006100"></option>
              <option value="#0047b2"></option>
              <option value="#6b24b2"></option>
              <option value="#444444"></option>
              <option value="#5c0000"></option>
              <option value="#663d00"></option>
              <option value="#666600"></option>
              <option value="#003700"></option>
              <option value="#002966"></option>
              <option value="#3d1466"></option>
            </select></span>
            <span class="ql-formats"><select class="ql-size">
              <option value="small"></option>
              <option selected></option>
              <option value="large"></option>
              <option value="huge"></option>
            </select></span>
            <span class="ql-formats"><button type="button" class="ql-blockquote"></button></span>
            <span class="ql-formats"><button type="button" class="ql-list" value="ordered"></button></span>
            <span class="ql-formats"><button type="button" class="ql-list" value="bullet"></button></span>
            <span class="ql-formats"><button type="button" class="ql-link"></button></span>
            <span class="ql-formats">
            <button type="button" @click="imgClick" style="outline:none">
            <svg viewBox="0 0 18 18"> <rect class="ql-stroke" height="10" width="12" x="3" y="4"></rect> <circle class="ql-fill" cx="6" cy="7" r="1"></circle> <polyline class="ql-even ql-fill" points="5 12 5 11 7 9 8 10 11 7 13 9 13 12 5 12"></polyline> </svg>
            </button>
          </span>
          <span class="ql-formats"><button type="button" class="ql-video"></button></span>
          </div>
</quilleditor>
  </div>
</template>
<script>
import {quillEditor} from 'vue-quill-editor'
export default {
  props: {
    value: {
      type: String,
      default: ''
    },
    // 上传图片的地址
    uploadUrl: {
      type: String,
      default: 'http://localhost:8081/sectioninfo/saveimg'
    },
    // 上传图片的file控件name
    fileName: {
      type: String,
      default: 'file'
    }
  },
  data () {
    return {
      content: '',
      editorOption: {
        modules: {
          toolbar: '#toolbar'
        },
        placeholder: ''
      }
    }
  },
  methods: {
    onChange () {
      this.$emit('input', this.content)
    },
    // 选择上传图片切换
    onFileChange (e) {
      var self = this
      var fileInput = e.target
      if (fileInput.files.length === 0) {
        return
      }
      this.editor.focus()
      if (fileInput.files[0].size > 1024 * 1024 * 100) {
        this.$alert('图片不能大于600KB', '图片尺寸过大', {
          confirmButtonText: '确定',
          type: 'warning'
        })
      }
      var data = new FormData()
      data.append(this.fileName, fileInput.files[0])
      this.$axios.post(this.uploadUrl, data).then(function (res) {
        if (res.data) {
          self.editor.insertEmbed(self.editor.getSelection().index, 'image', res.data.data.url)
          // self.editor.insertEmbed(self.editor.getSelection().index, 'image', 'http://img.mukewang.com/5b0feaa20001b7b511950738.png')
        }
      })
    },
    // 点击上传图片按钮
    imgClick () {
      if (!this.uploadUrl) {
        console.log('no editor uploadUrl')
        return
      }
      // 内存创建input file
      var input = document.createElement('input')
      input.type = 'file'
      input.name = this.fileName
      input.accept = 'image/jpeg,image/png,image/jpg,image/gif'
      input.onchange = this.onFileChange
      input.click()
    },
    test () {
      console.log(this.content)
    }
  },
  computed: {
    editor () {
      return this.$refs.myTextEditor.quill
    }
  },
  components: {
    'quilleditor': quillEditor
  },
  mounted () {
    this.content = this.value
  },
  watch: {
    'value' (newVal, oldVal) {
      if (this.editor) {
        if (newVal !== this.content) {
          this.content = newVal
        }
      }
    }
  }
}
</script>
