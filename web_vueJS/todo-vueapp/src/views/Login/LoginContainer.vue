<template>
  <div class="LoginContainer">
    <LoginComponent v-if="isLoginPage" @switchPage="switchPage" />
    <SignupComponent v-if="!isLoginPage" @switchPage="switchPage" />
  </div>
</template>

<script>
import SignupComponent from "../../components/Login/SignupComponent.vue";
import LoginComponent from "../../components/Login/LoginComponent.vue";
export default {
  name: "LoginContainer",
  components: { SignupComponent, LoginComponent },
  data() {
    return {
      isLoginPage: true,
    };
  },
  methods: {
    switchPage() {
      this.isLoginPage = !this.isLoginPage;
    },
  },
};
</script>

<style scoped>
.LoginContainer {
  width: 100vw;
  height: 100vh;
  overflow: scroll;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
