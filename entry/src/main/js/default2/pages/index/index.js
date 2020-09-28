import router from '@system.router';
export default {
    data: {
        title: ""
    },
    onInit() {
        this.title = this.$t('strings.world');
    },
    jumpfood() {
        router.push({
            uri: 'pages/news/news'
        })
        console.info('跳转到 pages/news/news');
    }
}
