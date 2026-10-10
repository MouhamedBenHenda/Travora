package org.example.travora.Mapper;

import org.example.travora.dto.response.EventGroupResponse;
import org.example.travora.model.GroupEvent;
import org.springframework.stereotype.Service;

@Service
public class GroupEventMapper {

    public EventGroupResponse fromGroupEvent(GroupEvent groupEvent) {

        EventGroupResponse response = new EventGroupResponse();

        response.setId(groupEvent.getId());
        response.setName(groupEvent.getName());
        response.setDescription(groupEvent.getDescription());

        response.setUserId(
                groupEvent.getUser() == null
                        ? null
                        : groupEvent.getUser().getId()
        );

        response.setEventId(
                groupEvent.getEvent() == null
                        ? null
                        : groupEvent.getEvent().getId()
        );

        return response;
    }
}
