document.addEventListener('DOMContentLoaded', () => {
    const fileInput = document.getElementById('fileInput');
    const filePreview = document.getElementById('filePreview');
    const formatButtons = document.querySelectorAll('[data-format]');
    const exportBtn = document.getElementById('exportBtn');
    
    // 文件上传预览
    fileInput.addEventListener('change', (e) => {
        const file = e.target.files[0];
        if (file) {
            filePreview.innerHTML = `
                <div class="alert alert-success">
                    <i class="bi bi-file-earmark-check"></i>
                    已选择文件：${file.name}
                    <small class="d-block mt-1">${(file.size/1024).toFixed(2)}KB</small>
                </div>
            `;
        }
    });

    // 格式选择交互
    formatButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            formatButtons.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
        });
    });

    // 导出功能
    exportBtn.addEventListener('click', async () => {
        const selectedFormat = document.querySelector('.btn-group .active')?.dataset.format;
        
        if (!selectedFormat) {
            alert('请选择导出格式');
            return;
        }

        try {
            // TODO: 调用后端API
            alert(`正在导出${selectedFormat.toUpperCase()}格式文件...`);
        } catch (error) {
            console.error('导出失败:', error);
            alert('导出失败，请检查控制台');
        }
    });
});