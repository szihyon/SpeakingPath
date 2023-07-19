import { signUp, consultSignUp } from "./AuthAPI";

export const onChangEmail = function(e, setEmail, setEmailMessage) {
  const currentEmail = e.target.value
  setEmail(currentEmail)
  const emailRegExp =
    /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/

  if (!emailRegExp.test(currentEmail)) {
    setEmailMessage("이메일의 형식이 올바르지 않습니다!");
    setEmail(currentEmail);
  } else {
    setEmailMessage("사용 가능한 이메일 입니다.");
    setEmail(currentEmail);
  }
}

export const onChangeId = function(e, setId, setIdMessage) {
  const currentId = e.target.value
  setId(currentId)
  const idRegExp = /^[a-zA-z0-9]{4,12}$/

  if (!idRegExp.test(currentId)) {
    setIdMessage("4-12사이 대소문자 또는 숫자만 입력해 주세요!")
    setId(currentId)
  } else {
    setIdMessage("사용가능한 아이디 입니다.")
    setId(currentId)
  }
}

export const onChangePassword = function(e, setPassword, setPasswordMessage) {
  const currentPassword = e.target.value;
  setPassword(currentPassword);
  const passwordRegExp =
    /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/
  if (!passwordRegExp.test(currentPassword)) {
    setPasswordMessage(
      "숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요."
    );
    setPassword(currentPassword)
  } else {
    setPasswordMessage("")
    setPassword(currentPassword)
  }
}

export const onChangePasswordConfirm = function(e, password, setPasswordConfirm, setPasswordConfirmMessage) {
  const currentPasswordConfirm = e.target.value;
  setPasswordConfirm(currentPasswordConfirm);
  if (password !== currentPasswordConfirm) {
    setPasswordConfirmMessage("같은 비밀번호를 입력해주세요.");
    setPasswordConfirm(currentPasswordConfirm);
  } else {
    setPasswordConfirmMessage("");
    setPasswordConfirm(currentPasswordConfirm);
  }
}


export const clickSignup = async (e, data) => {
  e.preventDefault()
  signUp(data)
  // .then((response) => {
  //     window.location.href = `/login`;
  // }).catch((error) => {
  //     console.log(error);
  // });
}

export const clickConsultantSignup = 
async (e, data) => {
  e.preventDefault()
  consultSignUp(data)
  .then((response) => {
      window.location.href = `/login`;
  }).catch((error) => {
      console.log(error);
  });
}