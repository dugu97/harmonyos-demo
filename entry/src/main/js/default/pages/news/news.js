import router from '@system.router';
const MEG_CODE_INSERT = 1001
// abilityType: 0-Ability; 1-Internal Ability
const ABILITY_TYPE_EXTERNAL = 0;
const ABILITY_TYPE_INTERNAL = 1;
// syncOption(Optional, default sync): 0-Sync; 1-Async
const ACTION_SYNC = 0;
const ACTION_ASYNC = 1;

const globalRef = Object.getPrototypeOf(global) || global
// 注入regeneratorRuntime
globalRef.regeneratorRuntime = require('@babel/runtime/regenerator')
export default {
    data: {
        cartText: 'Add To Cart',
        cartStyle: 'cart-text',
        isCartEmpty: true,
        jumpText: '演示路由跳转',
        cartStyle1: 'cart-text',
        descriptionFirstParagraph: 'This is the food page including fresh fruit, meat, snack and etc. You can pick whatever you like and add it to your Cart. Your order will arrive within 48 hours. We gurantee that our food is organic and healthy. Feel free to ask our 24h online service to explore more about our platform and products.',
        imageList: ['/common/food_000.jpg', '/common/food_001.jpg', '/common/food_002.jpg', '/common/food_003.jpg'],
    },

    swipeToIndex(index) {
        this.$element('swiperImage').swipeTo({index: index});
    },

    addCart() {
        if (this.isCartEmpty) {
            this.cartText = 'Cart + 1';
            this.cartStyle = 'add-cart-text';
            this.isCartEmpty = false;
            console.info('start insert');
            this.insertNews();
            console.info('end insert');
        }else{
            this.cartText = 'Add To Cart'
            this.cartStyle = 'cart-text'
            this.isCartEmpty = true;
        }
    },

    jumpIndex(){
        router.push({
            uri: 'pages/index/index'
        })
        console.info('跳转到 pages/index/index');
    },

    getFocus() {
        if (this.isCartEmpty) {
            this.cartStyle = 'cart-text-focus';
        }
    },

    lostFocus() {
        if (this.isCartEmpty) {
            this.cartStyle = 'cart-text';
        }
    },

    insertNews: async function(){
        console.info('A');
        var news = {};
        news.title = 'jsTitle';
        news.content = 'jsContent';

        var action = {};
        action.bundleName = 'com.example.myapplication';
        action.abilityName = 'com.example.myapplication.service.ServiceForJsInternalAbility';
        action.messageCode = MEG_CODE_INSERT;
        action.data = news;
        action.abilityType = ABILITY_TYPE_INTERNAL;
        action.syncOption = ACTION_ASYNC;

        console.info('A1');

        var result = await FeatureAbility.callAbility(action);

        console.info(result.toString());

        var ret = JSON.parse(result);

        console.info('C');

        if (ret.code == 0) {
            console.info('成功插入');
        } else {
            console.error('plus error code:' + JSON.stringify(ret.code));
        }
    }
}