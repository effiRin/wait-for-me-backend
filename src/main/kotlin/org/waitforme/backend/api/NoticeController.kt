package org.waitforme.backend.api

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.waitforme.backend.model.LoginAdmin
import org.waitforme.backend.model.request.notice.NoticeRequest
import org.waitforme.backend.model.response.notice.NoticeListResponse
import org.waitforme.backend.model.response.notice.NoticeResponse
import org.waitforme.backend.model.response.notice.NoticeSaveResponse
import org.waitforme.backend.service.NoticeService

@RestController
@Tag(name = "공지 API")
@RequestMapping("/v1/notice")
class NoticeController(
    private val noticeService: NoticeService,
) {

    // 공지 리스트 - 페이징 적용 X
    @GetMapping("")
    fun getNoticeList(): List<NoticeListResponse> =
        noticeService.findNoticeList()

    @GetMapping("/{id}")
    fun getNotice(
        @Parameter(name = "id", description = "공지 ID", `in` = ParameterIn.PATH)
        @PathVariable
        id: Int,
    ): NoticeResponse =
        noticeService.findNotice(noticeId = id)

    @PreAuthorize("hasRole('ADMIN')") // defaultPrefix 'ROLE'이 붙어야 해서 hasAuthority 대신 hasRole
    @PostMapping("")
    fun createNotice(
        @Parameter(hidden = true) @AuthenticationPrincipal loginAdmin: LoginAdmin,
        @RequestBody noticeRequest: NoticeRequest,
    ): NoticeSaveResponse =
        noticeService.saveNotice(request = noticeRequest, loginAdmin = loginAdmin)

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    fun modifyNotice(
        @Parameter(hidden = true) @AuthenticationPrincipal loginAdmin: LoginAdmin,
        @Parameter(name = "id", description = "공지 ID", `in` = ParameterIn.PATH) @PathVariable id: Int,
        @RequestBody noticeRequest: NoticeRequest,
    ): NoticeSaveResponse =
        noticeService.saveNotice(noticeId = id, request = noticeRequest, loginAdmin = loginAdmin)

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    fun deleteNotice(
        @Parameter(hidden = true) @AuthenticationPrincipal loginAdmin: LoginAdmin,
        @Parameter(name = "id", description = "공지 ID", `in` = ParameterIn.PATH)
        @PathVariable
        id: Int,
    ): Boolean =
        noticeService.deleteNotice(noticeId = id, loginAdmin = loginAdmin)
}
