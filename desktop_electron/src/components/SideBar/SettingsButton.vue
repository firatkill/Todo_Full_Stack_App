<template>
  <div
    @click="openSettingsModal"
    style="cursor: pointer"
    class="SettingsButton"
  >
    <img
      style="width: 3rem; cursor: pointer"
      class="ms-3 settingsButtonImg"
      src="@/assets/settings.svg"
    />
  </div>
  <div theme="light" class="settingsModal" :class="!isModalOpen && 'hide'">
    <div class="modalContainer">
      <div @click="closeSettingsModal" class="modal_closeButton">X</div>
      <div className="modalTitle">
        <h1>Change Password</h1>
        <p>Change your password by providing the information below.</p>
      </div>
      <form @submit="submitHandler" className="modalContent">
        <label>Current Password</label>
        <input v-model="currentPassword" required type="password" />
        <label>New Password</label>
        <input v-model="newPassword" required type="password" />
        <label>New Password Again</label>
        <input v-model="newPassword2" required type="password" />
        <div className="modalFooter">
          <button @click.prevent="changePassword">Change Password</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "SettingsButton",
  data() {
    return {
      isModalOpen: false,
      currentPassword: "",
      newPassword: "",
      newPassword2: "",
    };
  },
  methods: {
    openSettingsModal() {
      this.isModalOpen = true;
    },
    closeSettingsModal() {
      this.isModalOpen = false;
    },
    changePassword() {
      if (this.newPassword === this.newPassword2) {
        const response = this.$store.dispatch("changePassword", {
          oldPassword: this.currentPassword,
          newPassword: this.newPassword,
        });
        if (response) {
          this.currentPassword = "";
          (this.newPassword = ""),
            (this.newPassword2 = ""),
            (this.isModalOpen = false);
        } else {
          alert("Current password is incorrect.");
        }
      } else {
        alert("Make sure passwords match.");
      }
    },
  },
};
</script>

<style scoped>
.settingsButtonImg:hover {
  animation-name: spin;
  animation-duration: 1.5s;
  animation-timing-function: ease-in-out;
  transform-origin: center;
  animation-fill-mode: forwards;
  animation-iteration-count: 1;
}
@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
.modal_closeButton {
  background-color: red;
  position: absolute;
  top: 0;
  right: 0;
  padding: 0.75rem 1rem;
  cursor: pointer;
  border-bottom-left-radius: 20px;
}
.modal_closeButton:hover {
  background-color: hsl(235, 24%, 19%);
  color: hsl(234, 39%, 85%);
  transition: all 0.25s;
}
.settingsButton_button {
  background-color: red;
  padding: 0.75rem 1.5rem;
  border-radius: 20px;
  border: none;
  position: absolute;
  right: 10px;
  top: 10px;
  color: red;
  background-color: transparent;
  border: 2px solid red;
  cursor: pointer;
  font-size: 1.25rem;
}
.settingsModal {
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 100;
  display: flex;
  align-items: center;
  justify-content: center;
}
.hide {
  display: none !important;
}
.modalContainer {
  max-width: 800px;
  width: 90%;
  font-size: 1.7vw;

  position: relative;

  margin: auto;
  display: flex;
  flex-direction: column;
  padding: 2vw 2vw;

  border-radius: 1vw;
}

.settingsModal .modalContainer {
  background-color: White;
  color: hsl(235, 19%, 35%);
}
.modalContainer label {
  margin-bottom: 0.2vw;
  margin-top: 1vw;
}

.settingsModal input {
  color: hsl(234, 39%, 85%);
  background-color: hsl(235, 19%, 35%) !important;
}

.modalContainer input {
  font-size: 2.5vw;
  height: 4.5vw;
  padding-left: 0.5vw;
  font-weight: bold;
  border-radius: 1vw;
  border: none;
  outline: none;
}
.modalTitle {
  border-bottom: 2px solid;
  padding-bottom: 0.3vw;
  margin-bottom: 1vw;
}
.modalTitle h1 {
  margin-bottom: 0.5vw;
}
.modalContent {
  display: flex;
  flex-direction: column;
}
.modalFooter {
  margin-top: 1vw;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.modalFooter button {
  background-color: transparent;
  font-size: 2vw;
  border-radius: 1vw;
  padding: 1vw 2vw;
  cursor: pointer;
  display: flex;

  font-weight: bold;
}
.settingsModal .modalFooter button {
  color: hsl(235, 19%, 35%);
  border: 0.2vw solid hsl(235, 19%, 35%);
}

.settingsModal .modalFooter button:hover {
  background-color: hsl(235, 19%, 35%);
  color: hsl(236, 33%, 92%);
}
</style>
